package ua.goit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.goit.model.dto.BookDto;
import ua.goit.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findBook")
@Configurable
public class FindBookServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FindBookServlet.class);
    private BookService service;

    @Override
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet.");
        String bookName = req.getParameter("bookName");
        List<BookDto> book = service.findBookByName(bookName);
        req.setAttribute("books", book);
        req.getRequestDispatcher("/WEB-INF/html/findBook.jsp").forward(req, resp);
    }

    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }
}
