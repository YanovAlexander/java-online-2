package ua.goit.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateProvider.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateProvider.destroy();
    }
}
