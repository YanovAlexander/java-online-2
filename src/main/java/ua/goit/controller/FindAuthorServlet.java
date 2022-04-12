package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.exception.AuthorNotFoundException;
import ua.goit.model.converter.AuthorConverter;
import ua.goit.model.dto.AuthorDto;
import ua.goit.repository.AuthorRepository;
import ua.goit.service.AuthorService;


import java.io.IOException;

@WebServlet(urlPatterns = "/findAuthor")
public class FindAuthorServlet extends HttpServlet {
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
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
