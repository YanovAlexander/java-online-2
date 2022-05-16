package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.model.dto.AuthorDto;
import ua.goit.model.dto.BookDto;
import ua.goit.service.AuthorPropertyEditor;
import ua.goit.service.AuthorService;
import ua.goit.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books/*")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping(path = "/findBookForm")
    public String findBookForm() {
        return "findBookForm";
    }

    @GetMapping(path = "/findBook")
    public ModelAndView findBook(@RequestParam(name = "bookName") String bookName, ModelAndView model) {
        List<BookDto> book = bookService.findBookByName(bookName);
        model.setViewName("findBook");
        model.addObject("books", book);
        return model;
    }

    @GetMapping(path = "/addBookForm")
    public String addBookForm(Model model) {
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBookForm";
    }

    @PostMapping(path = "/addBook")
    public String addBook(@ModelAttribute("bookDto") @Valid BookDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "addBookForm";
        }
        bookService.save(dto);
        return "bookAdded";
    }

    @ModelAttribute("bookDto")
    public BookDto getDefaultBookDto() {
        return new BookDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(AuthorDto.class, new AuthorPropertyEditor());
    }
}
