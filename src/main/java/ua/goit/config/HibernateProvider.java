package ua.goit.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateProvider implements DatabaseManager{
    private static SessionFactory sessionFactory;

    public HibernateProvider() {
        init();
    }

    @Override
    public Session getSession(){
        return sessionFactory.openSession();
    }

    public static synchronized void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static synchronized void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
