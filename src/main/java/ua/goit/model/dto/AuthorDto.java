package ua.goit.model.dto;

import java.util.Objects;
import java.util.Set;

public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<BookDto> books;

    public AuthorDto(String firstName, String lastName, String email, Set<BookDto> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.books = books;
    }

    public AuthorDto(Integer id, String firstName, String lastName, String email, Set<BookDto> books) {
        this(firstName, lastName, email, books);
        this.id = id;

    }

    public AuthorDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return id +
                ", " + firstName +
                ", " + lastName +
                ", " + email ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return id == authorDto.id && Objects.equals(firstName, authorDto.firstName) && Objects.equals(lastName, authorDto.lastName) && Objects.equals(email, authorDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
