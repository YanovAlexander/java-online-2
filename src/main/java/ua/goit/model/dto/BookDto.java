package ua.goit.model.dto;


import java.util.Set;

public class BookDto {
    private Long id;
    private Set<AuthorDto> author;
    private String name;
    private int countPages;

    public BookDto(Long id, String name, int countPages, Set<AuthorDto> author) {
        this.id = id;
        this.name = name;
        this.countPages = countPages;
        this.author = author;
    }

    public BookDto() {
    }

    public Set<AuthorDto> getAuthors() {
        return author;
    }

    public void setAuthors(Set<AuthorDto> author) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}