package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.AuthorRepository;
import ua.goit.repository.BookRepository;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;


import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {
    private BookService bookService;
    private AuthorService authorService;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        bookService = new BookService(new BookRepository(dbConnector), new BookConverter(), new AuthorConverter());
        authorService = new AuthorService(new AuthorRepository(dbConnector), new AuthorConverter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        Integer countPages = Integer.parseInt(req.getParameter("countPages"));
        Integer authorId = Integer.parseInt(req.getParameter("authorId"));
        BookDto bookDto = new BookDto();
        bookDto.setName(bookName);
        bookDto.setCountPages(countPages);
        AuthorDto authorDto = authorService.findById(authorId);
        bookDto.setAuthors(Set.of(authorDto));
        bookService.save(bookDto, authorDto);
        req.getRequestDispatcher("/WEB-INF/html/bookAdded.jsp").forward(req, resp);
    }
}
