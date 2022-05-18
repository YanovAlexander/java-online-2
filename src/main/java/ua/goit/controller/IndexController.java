package ua.goit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @PostMapping("/")
    public String getIndexPost() {
        return "index";
    }
}
