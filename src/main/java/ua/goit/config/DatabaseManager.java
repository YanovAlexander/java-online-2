package ua.goit.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {

    Connection getConnection() throws SQLException;
}
