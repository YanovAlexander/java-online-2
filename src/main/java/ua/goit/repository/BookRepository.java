package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.sql.*;
import java.util.*;


public class BookRepository implements Repository<BookDao>, BookRepositoryCustom {

    private static final String INSERT_BOOK_AUTHOR = "INSERT INTO book_author(book_id, author_id) VALUES(?, ?);";

    private static final String SELECT_BY_NAME = "SELECT id, name, count_pages FROM book WHERE name=?;";

    private static final String SELECT_AUTHORS_BY_BOOK_ID = "SELECT a.id, a.first_name, a.last_name, a.email FROM book_author ba INNER JOIN author a ON a.id = ba.author_id WHERE ba.book_id = ?;";

    private static final String INSERT_BOOK = "INSERT INTO book (name, count_pages) VALUES(?, ?);";

    private final DatabaseManager manager;

    public BookRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public Integer save(BookDao bookDao) {
        try(Connection connection = manager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bookDao.getName());
            preparedStatement.setInt(2, bookDao.getCountPages());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public BookDao findByName(String name) {
        try(Connection connection = manager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_NAME)) {
            ps.setString(1, name);
           return mapToBookDao(ps.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<BookDao> findById(Integer id) {
        return null;
    }

    @Override
    public void addAuthorToBook(BookDao dao, AuthorDao authorDao) {
        try (Connection connection = manager.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_AUTHOR)) {
            ps.setLong(1, dao.getId());
            ps.setLong(2, authorDao.getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<AuthorDao> findAuthorsByBookId(Integer id) {
        try(Connection connection = manager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_AUTHORS_BY_BOOK_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapToAuthorDao(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Set<AuthorDao> mapToAuthorDao(ResultSet rs) throws SQLException {
        Set<AuthorDao> authors = new HashSet<>();
        while (rs.next()) {
            AuthorDao author = new AuthorDao();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setEmail(rs.getString("email"));
            authors.add(author);
        }
        return authors;
    }

    @Override
    public List<BookDao> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<BookDao> findByIds(Set<Integer> id) {
        return null;
    }

    private BookDao mapToBookDao(ResultSet rs) throws SQLException {
        BookDao dao = null;
        if (rs.next()) {
            dao = new BookDao();
            dao.setId(rs.getInt("id"));
            dao.setName(rs.getString("name"));
            dao.setCountPages(rs.getInt("count_pages"));
        }
        return dao;
    }
}
