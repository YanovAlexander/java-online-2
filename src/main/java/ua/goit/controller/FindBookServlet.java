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
import ua.goit.model.dto.BookDto;
import ua.goit.repository.BookRepository;
import ua.goit.service.BookService;


import java.io.IOException;

@WebServlet(urlPatterns = "/findBook")
public class FindBookServlet extends HttpServlet {
    private BookService service;

    @Override
    public void init() {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresHikariProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        service = new BookService(new BookRepository(dbConnector), new BookConverter(), new AuthorConverter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String bookName = req.getParameter("bookName");
         BookDto book = service.findBookByName(bookName);
         req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/html/findBook.jsp").forward(req, resp);
    }
}
