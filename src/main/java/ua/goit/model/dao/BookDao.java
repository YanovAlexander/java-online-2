package ua.goit.model.dao;

import java.util.Set;

public class BookDao {
    private Integer id;
    private Set<AuthorDao> authors;
    private String name;
    private Integer countPages;

    public BookDao(Integer id, String name, Integer countPages, Set<AuthorDao> author) {
        this.id = id;
        this.name = name;
        this.countPages = countPages;
        this.authors = author;
    }

    public BookDao() {
    }

    public Set<AuthorDao> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDao> author) {
        this.authors = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountPages() {
        return countPages;
    }

    public void setCountPages(Integer countPages) {
        this.countPages = countPages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}