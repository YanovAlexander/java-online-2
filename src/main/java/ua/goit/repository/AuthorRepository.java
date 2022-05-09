package ua.goit.repository;


import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;
import ua.goit.repository.rowmapper.AuthorRowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorRepository implements Repository<AuthorDao>{
    private static final String INSERT = "INSERT INTO author (first_name, last_name, email) VALUES (?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT id, first_name, last_name, email FROM author WHERE id=?;";
    private static final String FIND_BY_EMAIL = "SELECT * FROM author WHERE author.email = ?;";
    private static  final String FIND_ALL = "SELECT id, first_name, last_name, email FROM author;";
    private static final String FIND_BY_IDS = "SELECT id, first_name, last_name, email FROM author WHERE id IN (?);";

    private final DatabaseManager databaseManager;
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepository(DatabaseManager databaseManager, JdbcTemplate jdbcTemplate) {
        this.databaseManager = databaseManager;
        this.jdbcTemplate = jdbcTemplate;
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
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, new AuthorRowMapper()));
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
        try {
           return  jdbcTemplate.query(prepareInClauseSQL(id, FIND_BY_IDS), new AuthorRowMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
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
