package com.library.LibraryApplication.service;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.getById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
