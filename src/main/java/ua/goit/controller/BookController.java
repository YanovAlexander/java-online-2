package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.goit.controller.propertyEditors.AuthorDtoPropertyEditor;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping(path = "/form/find")
    public String getBookForm() {
        return "findBookForm";
    }

    @GetMapping(path = "/name/")
    public String getBook(@RequestParam("name") String bookName, Model model) {
        List<BookDto> book = bookService.findBookByName(bookName);
        model.addAttribute("books", book);
        return "findBook";
    }

    @GetMapping(path = "/form/add")
    public String getBookFrom(Model model) {
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBookForm";
    }

    @PostMapping
    public String addBook(@ModelAttribute("bookDto") @Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<AuthorDto> authors = authorService.findAll();
            model.addAttribute("authors", authors);
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
