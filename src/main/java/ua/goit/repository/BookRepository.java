package ua.goit.repository;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BookRepository implements Repository<BookDao>, BookRepositoryCustom {

    private static final String INSERT_BOOK_AUTHOR = "INSERT INTO book_author(book_id, author_id) VALUES(?, ?);";

    private final DatabaseManager manager;

    public BookRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(BookDao bookDao) {
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
}
