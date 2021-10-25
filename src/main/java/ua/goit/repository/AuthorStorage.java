package ua.goit.repository;

import ua.goit.model.Author;

public interface AuthorStorage {
    void add(Author author);
    Author remove(Author author);
    Author get(long id);
    void update(Author author);
}
