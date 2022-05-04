package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.goit.model.ErrorMessage;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
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
@Configurable
public class AddBookServlet extends HttpServlet {
    private BookService bookService;
    private AuthorService authorService;
    private BookValidator bookValidator;


    public AddBookServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }
}
