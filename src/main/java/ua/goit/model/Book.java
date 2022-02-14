package ua.goit.model;

import ua.goit.model.dto.AuthorDto;

public class Book extends Publication {
    private AuthorDto author;

    public Book(String name, int countPages, AuthorDto author) {
        super(name, countPages);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() + ", " + this.author.toString() + "}";
    }
}