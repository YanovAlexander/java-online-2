package ua.goit.model.dao;

public class JournalDao {
    private Integer id;
    private String name;
    private Integer number;
    private Integer countPages;
    private Integer publicationYear;

    public JournalDao(Integer id, String name, Integer countPages, Integer number, Integer publicationYear) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.countPages = countPages;
        this.publicationYear = publicationYear;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public JournalDao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
}
