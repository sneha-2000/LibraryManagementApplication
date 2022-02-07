package com.library.LibraryApplication.repository;

import com.library.LibraryApplication.entity.Book;
import com.library.LibraryApplication.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue,Long> {
    @Query("Select bookIssue from BookIssue bookIssue where bookIssue.book=:book")
    List<BookIssue> findIssueBookByBookId(Book book);
}
