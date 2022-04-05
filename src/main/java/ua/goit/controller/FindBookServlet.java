package ua.goit.controller;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
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

@WebServlet(urlPatterns = "/findBook")
public class FindBookServlet extends HttpServlet {
    private final BookService service;

    public FindBookServlet() {
        DatabaseManager dbConnector = new PostgresHikariProvider("localhost", 5432, "goit_library",
                "postgres", "12345");
        service = new BookService(new BookRepository(dbConnector), new BookConverter(), new AuthorConverter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String bookName = (String) req.getAttribute("bookName");
         BookDto book = service.findBookByName(bookName);
         req.setAttribute("book", book);
        req.getRequestDispatcher("/html/findBook.jsp").forward(req, resp);
    }
}
