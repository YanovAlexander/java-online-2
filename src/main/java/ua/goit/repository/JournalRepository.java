package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.JournalDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JournalRepository implements Repository<JournalDao>{
    private final DatabaseManager manager;
    private static final String INSERT =  "INSERT INTO journal (name, count_pages, number, publication_year) VALUES (?, ?, ?, ?)";

    public JournalRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(JournalDao journal) {
        try (Connection connection = manager.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, journal.getName());
            preparedStatement.setInt(2, journal.getCountPages());
            preparedStatement.setInt(3, journal.getNumber());
            preparedStatement.setInt(4, journal.getPublicationYear());
            preparedStatement.execute();
        }
            catch (SQLException e) {
            e.printStackTrace();
            }
    }
}
