package ua.goit.model.dao;

import java.util.Objects;

public class AuthorDao {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public AuthorDao(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public AuthorDao(long id, String firstName, String lastName, String email) {
        this(firstName, lastName, email);
        this.id = id;
    }

    public AuthorDao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
