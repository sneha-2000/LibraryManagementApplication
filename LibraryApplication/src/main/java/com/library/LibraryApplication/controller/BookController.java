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
    BookService bookService;

    @Autowired
    BookIssueService bookIssueService;

    @Autowired
    UserService userService;

    @Autowired
    UserBookingService userBookingService;

    /**
     * Admin can add books.
     */

    @RequestMapping("/addBooks")
    public String addBooks() {
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
     * @param model listing of all books.
     * @return
     */

    @RequestMapping("/adminBookList")
    public String showBookList(Model model){
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminBookList";
    }

    /**
     * Admin can delete a book.
     * @param request book id requesting.
     * @param model listing books after deleting.
     * @return
     */

    @PostMapping("/deleteBook")
    public String deleteRoom(HttpServletRequest request,Model model){
        Book book= bookService.getBookById(Long.parseLong(request.getParameter("bookId")));
        bookService.deleteBook(Long.parseLong(request.getParameter("bookId")));
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminBookList";
    }

    /**
     * Listing of books to issue it by admin.
     * @param model listing of books.
     * @return
     */

    @RequestMapping("/adminIssueList")
    public  String issueList(Model model) {
        model.addAttribute("lists",bookService.getAllBooks());
        return "adminIssueList";
    }

    /**
     * Issuing books using book id.
     * @param request book id requesting.
     * @return
     */

    @RequestMapping("/adminIssuesBook")
    public String bookIssueByDates(HttpServletRequest request){
        book = bookService.getBookById(Long.parseLong(request.getParameter("bookId")));
        return "bookIssueDatesByAdmin";
    }

    /**
     * Adding issue dates and return dates by admin for providing it to user.
     * @param request issue date, return date, price requesting.
     * @param model
     * @return
     */

    @PostMapping("/bookIssueDatesByAdmin")
    public String issueBooks(HttpServletRequest request, Model model) {

        String issueDate = request.getParameter("issueDate");
        String returnDate = request.getParameter("returnDate");
        Double price = Double.parseDouble(request.getParameter("price"));

        BookIssue bookIssue = new BookIssue(book,issueDate,price,returnDate);
        bookIssueService.saveIssueBook(bookIssue);

        return "adminHome";
    }

    /**
     * Listing of user details on admin side.
     * @param model user list.
     * @return
     */

    @RequestMapping("/adminUserList")
    public String users(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "adminUserList";
    }

    /**
     * Previous bookings of user on admin side.
     * @param model history of bookings on admin side.
     * @param request user id
     * @return
     */

    @RequestMapping("/adminUserBooking")
    public String userBookPaymentDetails(Model model,HttpServletRequest request){

        UserController.userId = Long.parseLong(request.getParameter("userId"));
        User user = userService.getUserById(UserController.userId);

        List<UserBooking> userBookings = userBookingService.getBookingByUsername(user.getUsername());
        model.addAttribute("userBookings",userBookings);
        return "adminUserBooking";
    }

    /**
     * Listing of all books that are available on user side.
     * @param model book list.
     * @return
     */

    @RequestMapping("/userBookDetails")
    public String books(Model model) {
        model.addAttribute("books",bookService.getAllBooks());
        return "userBookDetails";
    }

    /**
     * User issuing book options.
     * @param bookId for issuing book.
     * @param model title and other details of issuing.
     * @return
     */

    @RequestMapping("/userIssueBook/{bookId}")
    public String issueBook(@PathVariable Long bookId, Model model){
         userBook = bookService.getBookById(bookId);
        model.addAttribute("issues",bookIssueService.getIssuedBookByBookId(userBook));
        model.addAttribute("title",userBook.getTitle());
        return "userIssueBook";
    }

    /**
     * User payment bill with details.
     * @param request user id
     * @param model book and user details.
     * @return
     */

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

    /**
     * Successful payment page.
     * @return
     */

    @RequestMapping("/successfulPayment")
    public String successfulPayment(){
        return "successfulPayment";
    }

    /**
     * User history of bookings are listed here.
     * @param model history bookings.
     * @return
     */

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

}
