package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.BookIssue;
import com.library.LibraryApplication.repository.BookIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookIssueService {

    @Autowired
    BookIssueRepository bookIssueRepository;

    public BookIssue getIssuedBookById(Long id) {
        return  bookIssueRepository.getById(id);
    }

    public List<BookIssue> getIssuedBookByBookId(Book book){
        return bookIssueRepository.findIssueBookByBookId(book);
    }


    public void saveIssueBook(BookIssue bookIssue) {
         bookIssueRepository.save(bookIssue);
    }

}
