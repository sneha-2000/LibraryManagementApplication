package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.BookIssue;
import com.library.LibraryApplication.service.BookIssueService;
import com.library.LibraryApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookIssueService bookIssueService;

    @RequestMapping("/bookDetails")
    public String books(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "bookDetails";
    }

    @RequestMapping("/issueBook/{bookId}")
    public String issueBook(@PathVariable Long bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute("issues",bookIssueService.getIssuedBookByBookId(book));
        model.addAttribute("title",book.getTitle());
        System.out.println(bookIssueService.getIssuedBookByBookId(book));
        return "issueBook";
    }
}
