package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.BookIssue;
import com.library.LibraryApplication.entity.User;
import com.library.LibraryApplication.service.BookIssueService;
import com.library.LibraryApplication.service.BookService;
import com.library.LibraryApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
public class BookController {
    static Book book;
    @Autowired
    BookService bookService;

    @Autowired
    BookIssueService bookIssueService;

    @Autowired
    UserService userService;

    @RequestMapping("/userBookDetails")
    public String books(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "userBookDetails";
    }

    @RequestMapping("/userIssueBook/{bookId}")
    public String issueBook(@PathVariable Long bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute("issues",bookIssueService.getIssuedBookByBookId(book));
        model.addAttribute("title",book.getTitle());
        return "userIssueBook";
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

    /**
     * Admin can view the book list after adding it.
     * @param model
     * @return
     */

    @RequestMapping("/adminBookList")
    public String showBookList(Model model){
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminBookList";
    }

    @RequestMapping("/deleteBook")
    public String deleteRoom(HttpServletRequest request,Model model){
        Book book= bookService.getBookById(Long.parseLong(request.getParameter("bookId")));
        bookService.deleteBook(Long.parseLong(request.getParameter("bookId")));
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminBookList";
    }

    @RequestMapping("/adminIssueList")
    public  String issueList(Model model) {
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminIssueList";
    }

    @RequestMapping("/adminIssuesBook")
    public String bookIssueByDates(HttpServletRequest request){
         book = bookService.getBookById(Long.parseLong(request.getParameter("bookId")));
    return "bookIssueDatesByAdmin";
}

    @PostMapping("/bookIssueDatesByAdmin")
    public String issueBooks(HttpServletRequest request, Model model) {

        String issueDate = request.getParameter("issueDate");
        String returnDate = request.getParameter("returnDate");
        Double price = Double.parseDouble(request.getParameter("price"));

        BookIssue bookIssue = new BookIssue(book,issueDate,price,returnDate);
        bookIssueService.saveIssueBook(bookIssue);

        return "adminHome";
    }
//
//    @RequestMapping("/payment")
//    public String payment(HttpServletRequest request,Model model) {
//        Long issueBookId = Long.parseLong(request.getParameter("issueBookId"));
//        IssuedBooks issuedBooks =new IssuedBooks(UserController.userId,issueBookId);
//       issuedBooksService.saveIssuedBooks(issuedBooks);
//
//       User user = userService.findUserByUserName(userId);
//       Book book = bookService.getBookById(bookId);
//
//
//
//    }


}
