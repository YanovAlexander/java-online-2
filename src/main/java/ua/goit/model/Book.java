package ua.goit.model;

public class Book extends Publication {
    private Author author;

    public Book(String name, int countPages, Author author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() + ", " + this.author.toString() + "}";
    }
}