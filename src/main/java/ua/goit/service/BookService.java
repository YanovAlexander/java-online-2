package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.goit.exception.BookAlreadyExistsException;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Autowired
    public BookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(BookDto book) {
        List<BookDao> books = bookRepository.findByName(book.getName());
        if (!books.isEmpty()) {
            throw new BookAlreadyExistsException(String.format("book with name %s already exists", book.getName()));
        }

        bookRepository.save(bookConverter.to(book));
    }

    public List<BookDto> findBookByName(String name) {
        List<BookDao> books = bookRepository.findByName(name);
        return books.stream()
                .map(bookConverter::from)
                .collect(Collectors.toList());
    }
}
