package ua.goit.model;

public class Book extends Publication {
    private String author;

    public Book(String name, int countPages, String author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() + ", author = " + this.author + "}";
    }

}