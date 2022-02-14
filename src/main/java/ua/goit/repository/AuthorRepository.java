package ua.goit.repository;

import java.sql.Connection;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;

import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthorRepository implements Repository<AuthorDao>{
    private final DatabaseManager databaseManager;
    private static final String INSERT = "INSERT INTO author (first_name, last_name, email) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM author WHERE author.id = ?;";
    private static final String FIND_BY_EMAIL = "SELECT * FROM author WHERE author.email = ?;";

    public AuthorRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Optional<AuthorDao> findByID(Integer id) {
        try (Connection connection = databaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthorDao(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<AuthorDao> findByEmail(String email) {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthorDao(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(AuthorDao authorDao) {
        try ( Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, authorDao.getFirstName());
            preparedStatement.setString(2, authorDao.getLastName());
            preparedStatement.setString(3, authorDao.getEmail());
            preparedStatement.execute();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Optional<AuthorDao> mapToAuthorDao(ResultSet resultSet) throws SQLException {
        AuthorDao authorDao = null;
        while (resultSet.next()) {
            authorDao = new AuthorDao();
            authorDao.setId(resultSet.getInt("id"));
            authorDao.setFirstName(resultSet.getString("first_name"));
            authorDao.setLastName(resultSet.getString("last_name"));
            authorDao.setEmail(resultSet.getString("email"));
        }
        return Optional.ofNullable(authorDao);
    }
}
