package ua.goit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.converter.BookConverter;
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;
import ua.goit.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findBook")
public class FindBookServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FindBookServlet.class);
    private BookService service;

    @Override
    public void init() {
        DatabaseManager dbConnector = new HibernateProvider();
        AuthorConverter authorConverter = new AuthorConverter();
        service = new BookService(new BookRepository(dbConnector), new BookConverter(authorConverter), authorConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet.");
        String bookName = req.getParameter("bookName");
        List<BookDto> book = service.findBookByName(bookName);
        req.setAttribute("books", book);
        req.getRequestDispatcher("/WEB-INF/html/findBook.jsp").forward(req, resp);
    }
}
