package ua.goit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.repository.BookRepository;
import ua.goit.service.BookService;

@TestConfiguration
public class TestConfig {
    @Autowired
    private BookRepository bookRepository;

    @Bean
    public AuthorConverter authorConverter() {
        return new AuthorConverter();
    }

    @Bean
    public BookConverter bookConverter() {
        return new BookConverter(authorConverter());
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository, bookConverter());
    }
}
