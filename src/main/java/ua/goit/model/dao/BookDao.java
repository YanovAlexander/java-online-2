package ua.goit.model.dao;

import java.util.Set;

public class BookDao {
    private Integer id;
    private Set<AuthorDao> author;
    private String name;
    private int countPages;

    public BookDao(Integer id, String name, int countPages, Set<AuthorDao> author) {
        this.id = id;
        this.name = name;
        this.countPages = countPages;
        this.author = author;
    }

    public BookDao() {
    }

    public Set<AuthorDao> getAuthors() {
        return author;
    }

    public void setAuthors(Set<AuthorDao> author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}