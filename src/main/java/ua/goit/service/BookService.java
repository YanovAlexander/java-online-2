package ua.goit.service;

import ua.goit.model.converter.BookConverter;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public BookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public void save(BookDto book) {
        bookRepository.save(bookConverter.to(book));
    }

    public List<BookDto> findBookByName(String name) {
        List<BookDao> books = bookRepository.findByName(name);
        return books.stream()
                .map(bookConverter::from)
                .collect(Collectors.toList());
    }
}
