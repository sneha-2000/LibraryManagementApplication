package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.BookIssue;
import com.library.LibraryApplication.service.BookIssueService;
import com.library.LibraryApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
//        model.addAttribute("title",book.getTitle());
        System.out.println(bookIssueService.getIssuedBookByBookId(book));
        return "issueBook";
    }

    @RequestMapping("/addBooks")
    public String addBooks(){
        return "addBooks";
    }

    @PostMapping("/addBooks")
    public String addBooks(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        Integer releaseYear = (Integer.parseInt(request.getParameter("releaseYear")));
        Integer edition = (Integer.parseInt(request.getParameter("edition")));
        Book book = new Book(title, author, releaseYear, edition);
        bookService.saveBook(book);
        return "adminHome";
    }

    @RequestMapping("/adminBookList")
    public String showBookList(){
        return "adminBookList";
    }
}
