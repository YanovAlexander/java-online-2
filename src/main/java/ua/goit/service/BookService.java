package ua.goit.service;

import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookService(BookRepository bookRepository, BookConverter bookConverter, AuthorConverter authorConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }

    public void save(BookDto book) {
        bookRepository.save(bookConverter.to(book));
    }

    public void addAuthorToBook(BookDto book, AuthorDto dto) {
        bookRepository.addAuthorToBook(bookConverter.to(book), authorConverter.to(dto));
    }

    public List<BookDto> findBookByName(String name) {
        List<BookDao> bookDaos = bookRepository.findByName(name);
        return bookDaos.stream()
                .map(bookConverter::from)
                .collect(Collectors.toList());
    }
}
