package ua.goit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.model.dto.BookDto;
import ua.goit.service.BookService;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
