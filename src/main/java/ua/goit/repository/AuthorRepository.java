package ua.goit.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorRepository implements Repository<AuthorDao>{
    public static final Logger LOGGER = LogManager.getLogger(AuthorRepository.class);
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
        try (Session session = databaseManager.getSession()) {
            return session.createQuery("FROM AuthorDao ad WHERE ad.email=:email")
                    .setParameter("email", email)
                    .setResultListTransformer(Transformers.aliasToBean(AuthorDao.class))
                    .uniqueResultOptional();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Integer save(AuthorDao authorDao) {
//        try (Session session = databaseManager.getSession()) {
//
//        }
//        catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
        return null;
    }

    @Override
    public List<AuthorDao> findByName(String name) {
        return null;
    }

    @Override
    public Optional<AuthorDao> findById(Integer id) {
        try(Session session = databaseManager.getSession()) {
           return session.createQuery("FROM AuthorDao ad WHERE ad.id=:id")
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<AuthorDao> findAll() {
        try (Session session = databaseManager.getSession()){
            return session.createQuery("FROM AuthorDao").list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AuthorDao> findByIds(Set<Integer> id) {
        try (Session session = databaseManager.getSession()){
            return session.createQuery("FROM AuthorDao ad WHERE ad.id IN :ids")
                    .setParameter("ids", id)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
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
