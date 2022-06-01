package ua.goit.model.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class BookDto {
    private Integer id;
    private Set<AuthorDto> authors;
    private String name;
    private Integer countPages;

    public BookDto(Integer id, String name, Integer countPages, Set<AuthorDto> author) {
        this.id = id;
        this.name = name;
        this.countPages = countPages;
        this.authors = author;
    }

    public BookDto() {
    }

    @NotEmpty
    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> author) {
        this.authors = author;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(authors, bookDto.authors) && name.equals(bookDto.name) && countPages.equals(bookDto.countPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authors, name, countPages);
    }
}