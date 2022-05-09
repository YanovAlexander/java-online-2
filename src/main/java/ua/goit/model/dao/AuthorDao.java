package ua.goit.model.dao;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
@javax.persistence.Cacheable
public class AuthorDao {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<BookDao> books;

    public AuthorDao(String firstName, String lastName, String email, Set<BookDao> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.books = books;
    }

    public AuthorDao(Integer id, String firstName, String lastName, String email, Set<BookDao> books) {
        this(firstName, lastName, email, books);
        this.id = id;
    }

    public AuthorDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDao authorDao = (AuthorDao) o;
        return id == authorDao.id && Objects.equals(firstName, authorDao.firstName) && Objects.equals(lastName, authorDao.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }


    @ManyToMany(mappedBy = "authors")
    public Set<BookDao> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDao> books) {
        this.books = books;
    }
}
