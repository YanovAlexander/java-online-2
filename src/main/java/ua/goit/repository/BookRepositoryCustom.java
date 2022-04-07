package ua.goit.repository;

import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

import java.util.Set;

public interface BookRepositoryCustom {

    void addAuthorToBook(BookDao dao, AuthorDao authorDao);

    Set<AuthorDao> findAuthorsByBookId(Integer id);
}
