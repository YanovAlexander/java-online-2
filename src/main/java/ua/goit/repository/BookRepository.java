package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class BookRepository implements Repository<BookDao>, BookRepositoryCustom {

    private static final String INSERT_BOOK_AUTHOR = "INSERT INTO book_author(book_id, author_id) VALUES(?, ?);";

    private static final String SELECT_BY_NAME = "SELECT id, name, count_pages FROM book WHERE name=?;";

    private final DatabaseManager manager;

    public BookRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(BookDao bookDao) {
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
    public Optional<BookDao> findById(int id) {
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
