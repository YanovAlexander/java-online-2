package ua.goit.model.dao;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="book")
@javax.persistence.Cacheable
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    public Set<AuthorDao> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDao> author) {
        this.authors = author;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "count_pages")
    public Integer getCountPages() {
        return countPages;
    }

    public void setCountPages(Integer countPages) {
        this.countPages = countPages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        BookDao bookDao = (BookDao) o;
        return Objects.equals(id, bookDao.id) && Objects.equals(authors, bookDao.authors) && Objects.equals(name, bookDao.name) && Objects.equals(countPages, bookDao.countPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authors, name, countPages);
    }
}