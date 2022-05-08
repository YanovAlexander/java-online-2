package ua.goit.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.ErrorMessage;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.AuthorRepository;
import ua.goit.repository.BookRepository;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;
import ua.goit.service.BookValidator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(AddBookServlet.class);
    private BookService bookService;
    private AuthorService authorService;
    private BookValidator bookValidator;

    @Override
    public void init() {
        DatabaseManager dbConnector = new HibernateProvider();
        final AuthorConverter authorConverter = new AuthorConverter();
        bookService = new BookService(new BookRepository(dbConnector), new BookConverter(authorConverter), authorConverter);
        authorService = new AuthorService(new AuthorRepository(dbConnector), authorConverter);
        bookValidator = new BookValidator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorMessage errorMessage = bookValidator.validateCreateBook(req);
        if (!errorMessage.getErrors().isEmpty()) {
            List<AuthorDto> authors = authorService.findAll();
            req.setAttribute("authors", authors);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/html/addBookForm.jsp").forward(req, resp);
            return;
        }

        String bookName = req.getParameter("bookName");
        Integer countPages = Integer.parseInt(req.getParameter("countPages"));
        Set<Integer> authorIds = Arrays.stream(req.getParameterValues("authorId"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        BookDto bookDto = new BookDto();
        bookDto.setName(bookName);
        bookDto.setCountPages(countPages);
        Set<AuthorDto> authorDtos = authorService.findByIds(authorIds);
        bookDto.setAuthors(authorDtos);
        bookService.save(bookDto);
        req.getRequestDispatcher("/WEB-INF/html/bookAdded.jsp").forward(req, resp);
    }
}
