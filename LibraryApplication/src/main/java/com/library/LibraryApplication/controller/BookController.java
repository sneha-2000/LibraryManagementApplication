package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/bookDetails")
    public String books(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "bookDetails";
    }
}
