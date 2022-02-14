package com.library.LibraryApplication.controller;

import com.library.LibraryApplication.entity.*;
import com.library.LibraryApplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {
    static Book book;
    static Book userBook;
    static Long issueId;

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Autowired
    BookIssueService bookIssueService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    UserBookingService userBookingService;

    @RequestMapping("/userBookDetails")
    public String books(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "userBookDetails";
    }

    @RequestMapping("/userIssueBook/{bookId}")
    public String issueBook(@PathVariable Long bookId, Model model){
         userBook = bookService.getBookById(bookId);
        model.addAttribute("issues",bookIssueService.getIssuedBookByBookId(userBook));
        model.addAttribute("title",userBook.getTitle());

//        System.out.println(book.getBookId());
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

    @PostMapping("/deleteBook")
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

    @PostMapping("/userPayment")
    public String payment(HttpServletRequest request,Model model) {

        issueId = Long.parseLong(request.getParameter("id"));
        BookIssue bookIssue = bookIssueService.getIssuedBookById(issueId);

        User user = userService.getUserById(UserController.userId);

        model.addAttribute("name",user.getUsername());
        model.addAttribute("title",userBook.getTitle());
        model.addAttribute("issueDate",bookIssue.getIssueDate());
        model.addAttribute("returnDate",bookIssue.getReturnDate());
        model.addAttribute("price",bookIssue.getPrice());

        UserBooking userBooking = new UserBooking(user.getUsername(), userBook.getTitle(),bookIssue.getIssueDate(),bookIssue.getReturnDate(),bookIssue.getPrice());
        userBookingService.saveUserBookings(userBooking);

        return "userPayment";
    }

    @RequestMapping("/successfulPayment")
    public String successfulPayment(){
        return "successfulPayment";
    }

    @PostMapping("/userBookingDetails")
    public String userBookingDetails(Model model) {
        User user = userService.getUserById(UserController.userId);

        List<UserBooking> userBooking = userBookingService.getBookingByUsername(user.getUsername());
        model.addAttribute("bookings",userBooking);

        return "userBookingDetails";

    }

    @RequestMapping("/userBookingDetails")
    public String booking(Model model){
        User user = userService.getUserById(UserController.userId);

        List<UserBooking> userBooking = userBookingService.getBookingByUsername(user.getUsername());
        model.addAttribute("bookings",userBooking);

        return "userBookingDetails";
    }

    @RequestMapping("/adminUserList")
    public String users(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "adminUserList";
    }

    @RequestMapping("/adminUserBooking")
    public String userBookPaymentDetails(Model model,HttpServletRequest request){

        UserController.userId = Long.parseLong(request.getParameter("userId"));
        User user = userService.getUserById(UserController.userId);

        List<UserBooking> userBookings = userBookingService.getBookingByUsername(user.getUsername());
        model.addAttribute("userBookings",userBookings);
        return "adminUserBooking";
    }


}
