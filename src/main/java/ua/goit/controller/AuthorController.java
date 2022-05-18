package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.model.dto.AuthorDto;
import ua.goit.service.AuthorService;

@Controller
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/id/{id}")
    public ModelAndView getAuthor(@PathVariable("id") Integer authorId, ModelAndView modelAndView) {
        AuthorDto author = authorService.findById(authorId);
        modelAndView.addObject("author", author);
        modelAndView.setViewName("findAuthor");
        return modelAndView;
    }
}
