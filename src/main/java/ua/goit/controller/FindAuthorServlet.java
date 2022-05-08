package ua.goit.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.exception.AuthorNotFoundException;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.dto.AuthorDto;
import ua.goit.repository.AuthorRepository;
import ua.goit.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findAuthor")
public class FindAuthorServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(FindAuthorServlet.class);
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        AuthorRepository authorRepository = new AuthorRepository(dbConnector);
        authorService = new AuthorService(authorRepository, new AuthorConverter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorId = req.getParameter("authorId");
        AuthorDto author;
        try {
            author = authorService.findById(Integer.parseInt(authorId));
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                req.setAttribute("exception", "Wrong number format");
            } else if (ex instanceof AuthorNotFoundException) {
                req.setAttribute("exception", ex.getMessage());
            } else {
                req.setAttribute("exception", "Opps, something get wrong. Please try again");
            }
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("author", author);
        req.getRequestDispatcher("/WEB-INF/html/findAuthor.jsp").forward(req, resp);
    }
}
