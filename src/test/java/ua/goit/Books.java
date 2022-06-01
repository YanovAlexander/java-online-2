package ua.goit;

import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Books {
    public static List<BookDao> prepareBooks() {
        List<BookDao> books = new ArrayList<>();
        books.add(new BookDao(1, "book1", 100, prepareAuthors()));
        books.add(new BookDao(2, "book2", 1001, prepareAuthors()));
        return books;
    }

    public static List<BookDao> prepareBooksForDb() {
        List<BookDao> books = new ArrayList<>();
        books.add(new BookDao(null, "book1", 100, prepareAuthorsForDb()));
        books.add(new BookDao(null, "book2", 1001, prepareAuthorsForDb()));
        return books;
    }

    public static BookDao prepareBook(Integer id, String name, Integer countPages, Set<AuthorDao> authors) {
        BookDao bookDao = new BookDao();
        bookDao.setId(id);
        bookDao.setName(name);
        bookDao.setCountPages(countPages);
        bookDao.setAuthors(authors);
        return bookDao;
    }

    public static Set<AuthorDao> prepareAuthors() {
        Set<AuthorDao> authors = new HashSet<>();
        authors.add(prepareAuthor(1, "oleksandr", "yanov", "yanov@gmail.com"));
        authors.add(prepareAuthor(2, "olek", "olek", "olek@gmail.com"));
        return authors;
    }

    public static Set<AuthorDao> prepareAuthorsForDb() {
        Set<AuthorDao> authors = new HashSet<>();
        authors.add(prepareAuthor(null, "oleksandr", "yanov", "yanov@gmail.com"));
        authors.add(prepareAuthor(null, "olek", "olek", "olek@gmail.com"));
        return authors;
    }

    public static AuthorDao prepareAuthor(Integer id, String firstName, String lastName, String email) {
        AuthorDao authorDao = new AuthorDao();
        authorDao.setId(id);
        authorDao.setFirstName(firstName);
        authorDao.setLastName(lastName);
        authorDao.setEmail(email);
        return authorDao;
    }
}
