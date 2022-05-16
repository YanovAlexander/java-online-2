package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.goit.controller.propertyEditors.AuthorDtoPropertyEditor;
import ua.goit.model.ErrorMessage;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;
import ua.goit.service.BookValidator;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookValidator = bookValidator;
    }

    @GetMapping(path = "/findBookForm")
    public String getBookForm() {
        return "findBookForm";
    }

    @GetMapping(path = "/findBook")
    public String getBook(@RequestParam("bookName") String bookName, Model model) {
        List<BookDto> book = bookService.findBookByName(bookName);
        model.addAttribute("books", book);
        return "findBook";
    }

    @GetMapping(path = "/addBookForm")
    public String getBookFrom(Model model) {
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBookForm";
    }

    @PostMapping(path = "/addBook")
    public String addBook(@ModelAttribute("bookDto") BookDto bookDto, Model model) {
        ErrorMessage errorMessage = bookValidator.validateCreateBook(bookDto);

        if (!errorMessage.getErrors().isEmpty()) {
            List<AuthorDto> authors = authorService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("errorMessage", errorMessage);
            return "addBookForm";
        }

        bookService.save(bookDto);
        return "bookAdded";
    }

    @ModelAttribute
    public BookDto getDefaultBookDto() {
        return new BookDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(AuthorDto.class, new AuthorDtoPropertyEditor());
    }
}
