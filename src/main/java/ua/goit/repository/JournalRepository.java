package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.JournalDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class JournalRepository implements Repository<JournalDao>{
    private final DatabaseManager manager;
    private static final String INSERT =  "INSERT INTO journal (name, count_pages, number, publication_year) VALUES (?, ?, ?, ?)";

    public JournalRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public Integer save(JournalDao journal) {
//        try (Connection connection = manager.getSession();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
//            preparedStatement.setString(1, journal.getName());
//            preparedStatement.setInt(2, journal.getCountPages());
//            preparedStatement.setInt(3, journal.getNumber());
//            preparedStatement.setInt(4, journal.getPublicationYear());
//            preparedStatement.execute();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                return generatedKeys.getInt(1);
//            }
//        }
//            catch (SQLException e) {
//            e.printStackTrace();
//            }
        return null;
    }

    @Override
    public List<JournalDao> findByName(String name) {
        return null;
    }

    @Override
    public Optional<JournalDao> findById(Integer id) {
        return null;
    }

    @Override
    public List<JournalDao> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<JournalDao> findByIds(Set<Integer> id) {
        return null;
    }
}
