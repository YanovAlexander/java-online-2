package ua.goit.controller;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
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
import java.util.List;

@WebServlet(urlPatterns = "/addBookForm")
public class AddBookFormServlet extends HttpServlet {
    private AuthorService service;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        service = new AuthorService(new AuthorRepository(dbConnector), new AuthorConverter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AuthorDto> authors = service.findAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/WEB-INF/html/addBookForm.jsp").forward(req, resp);
    }
}
