package ua.goit.service;

import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookService(BookRepository bookRepository, ua.goit.model.converter.BookConverter bookConverter, AuthorConverter authorConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }

    public void save(BookDto book, AuthorDto author) {
        Integer bookId = bookRepository.save(bookConverter.to(book));
        book.setId(bookId);
        addAuthorToBook(book, author);
    }

    public void addAuthorToBook(BookDto book, AuthorDto dto) {
        bookRepository.addAuthorToBook(bookConverter.to(book), authorConverter.to(dto));
    }

    public BookDto findBookByName(String name) {
        BookDto book = bookConverter.from(bookRepository.findByName(name));
        Set<AuthorDto> authors = bookRepository.findAuthorsByBookId(book.getId())
                .stream()
                .map(authorConverter::from)
                .collect(Collectors.toSet());
        book.setAuthors(authors);
        return book;
    }


}
