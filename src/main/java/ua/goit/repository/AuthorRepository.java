package ua.goit.repository;

import java.sql.Connection;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorRepository implements Repository<AuthorDao>{
    private final DatabaseManager databaseManager;
    private static final String INSERT = "INSERT INTO author (first_name, last_name, email) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT id, first_name, last_name, email FROM author WHERE id=?;";
    private static final String FIND_BY_EMAIL = "SELECT * FROM author WHERE author.email = ?;";
    private static  final String FIND_ALL = "SELECT id, first_name, last_name, email FROM author;";
    private static final String FIND_BY_IDS = "SELECT id, first_name, last_name, email FROM author WHERE id IN (?);";

    public AuthorRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
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
    public Integer save(AuthorDao authorDao) {
        try ( Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, authorDao.getFirstName());
            preparedStatement.setString(2, authorDao.getLastName());
            preparedStatement.setString(3, authorDao.getEmail());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public AuthorDao findByName(String name) {
        return null;
    }

    @Override
    public Optional<AuthorDao> findById(Integer id) {
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

    @Override
    public List<AuthorDao> findAll() {
        try (Connection connection = databaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthorDaos(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<AuthorDao> findByIds(Set<Integer> id) {
        String findByIds = prepareInClauseSQL(id, FIND_BY_IDS);

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findByIds)){

            return mapToAuthorDaos(preparedStatement.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
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

    private List<AuthorDao> mapToAuthorDaos(ResultSet resultSet) throws SQLException {
        List<AuthorDao> authorDaos = new ArrayList<>();
        while (resultSet.next()) {
            AuthorDao authorDao = new AuthorDao();
            authorDao.setId(resultSet.getInt("id"));
            authorDao.setFirstName(resultSet.getString("first_name"));
            authorDao.setLastName(resultSet.getString("last_name"));
            authorDao.setEmail(resultSet.getString("email"));
            authorDaos.add(authorDao);
        }
        return authorDaos;
    }

    private String prepareInClauseSQL(Set<Integer> id, String sql) {
        String sqlIN = id.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "(", ")"));
        String findByIds = sql;
        findByIds = findByIds.replace("(?)", sqlIN);
        return findByIds;
    }
}
