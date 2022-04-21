package ua.goit.config;

import org.hibernate.Session;

public interface DatabaseManager {

    Session getSession();
}
