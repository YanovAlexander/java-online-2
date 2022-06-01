package ua.goit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.Books;
import ua.goit.config.TestConfig;
import ua.goit.exception.BookAlreadyExistsException;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dao.AuthorDao;
import ua.goit.model.dao.BookDao;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = TestConfig.class)
class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    AuthorConverter authorConverter = new AuthorConverter();

    BookConverter bookConverter = new BookConverter(authorConverter);

    @Test
    public void testFindBookByNameShouldReturnBook() {
        //given
        String bookName = "Book";
        when(bookRepository.findByName(bookName)).thenReturn(Books.prepareBooks());
        List<BookDto> books = Books.prepareBooks().stream()
                .map(book -> bookConverter.from(book))
                .collect(Collectors.toList());
        //when
        List<BookDto> resultBooks = bookService.findBookByName(bookName);

        //then
        assertThat(resultBooks, notNullValue());
        assertThat(resultBooks.size(), equalTo(2));
        assertThat(resultBooks, equalTo(books));
    }

    @Test
    public void testFindBookByNameWithNotExistsNameShouldReturnEmptyList() {
        //given
        String bookName = "Book";
        when(bookRepository.findByName(bookName)).thenReturn(List.of());
        //when
        List<BookDto> resultBooks = bookService.findBookByName(bookName);

        //then
        assertTrue(resultBooks.isEmpty());
    }

    @Test
    public void testSaveBookHappyPath() {
        //given
        String bookName = "oleksandr";
        BookDao book = Books.prepareBook(1, bookName, 100, new HashSet<>());
        when(bookRepository.findByName(bookName)).thenReturn(new ArrayList<>());
        //when
        bookService.save(bookConverter.from(book));

        //then
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSaveBookWithAlreadyExistsNameShouldThrowException() {
        //given
        String bookName = "oleksandr";
        BookDao book = Books.prepareBook(1, bookName, 100, new HashSet<>());
        when(bookRepository.findByName(bookName)).thenReturn(List.of(book));

        //when
        assertThrows(BookAlreadyExistsException.class, () -> bookService.save(bookConverter.from(book)),
                String.format("book with name %s already exists", bookName));
        //then
        verify(bookRepository, times(0)).save(book);
    }



}