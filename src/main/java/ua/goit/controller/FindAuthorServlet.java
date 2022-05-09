package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.goit.exception.AuthorNotFoundException;
import ua.goit.model.dto.AuthorDto;
import ua.goit.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findAuthor")
@Configurable
public class FindAuthorServlet extends HttpServlet {
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }
}
