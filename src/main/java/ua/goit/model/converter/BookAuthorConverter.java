package ua.goit.model.converter;

import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;

import java.util.Set;
import java.util.stream.Collectors;

public class BookAuthorConverter {
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookAuthorConverter(BookConverter bookConverter, AuthorConverter authorConverter) {
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }

    public BookDto convert(BookDao dao) {
        BookDto dto = bookConverter.from(dao);
        Set<AuthorDto> authors = dao.getAuthors().stream()
                .map(authorDao -> authorConverter.from(authorDao))
                .collect(Collectors.toSet());
        dto.setAuthors(authors);
        return dto;
    }

    public BookDao convert(BookDto dto) {
        BookDao dao = bookConverter.to(dto);
        Set<AuthorDao> authors = dto.getAuthors().stream()
                .map(authorDto -> authorConverter.to(authorDto))
                .collect(Collectors.toSet());
        dao.setAuthors(authors);
        return dao;
    }

    public AuthorDto convert(AuthorDao dao) {
        AuthorDto dto = authorConverter.from(dao);
        Set<BookDto> books = dao.getBooks().stream()
                .map(bookDao -> bookConverter.from(bookDao))
                .collect(Collectors.toSet());
        dto.setBooks(books);
        return dto;
    }

    public AuthorDao convert(AuthorDto dto) {
        AuthorDao dao = authorConverter.to(dto);
        Set<BookDao> books = dto.getBooks().stream()
                .map(bookDto -> bookConverter.to(bookDto))
                .collect(Collectors.toSet());
        dao.setBooks(books);
        return dao;
    }
}
