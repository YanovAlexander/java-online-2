package ua.goit.repository;

import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;

public interface BookRepositoryCustom {

    void addAuthorToBook(BookDao dao, AuthorDao authorDao);
}
