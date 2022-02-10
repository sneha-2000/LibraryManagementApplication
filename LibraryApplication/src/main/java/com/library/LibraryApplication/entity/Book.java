package com.library.LibraryApplication.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long bookId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int releaseYear;
    @Column(nullable = false)
    private int edition;
    @OneToMany(mappedBy = "book")
    private List<BookIssue> bookIssue;

    public Book() {
    }

    public Book(String title, String author, int releaseYear, int edition) {
        super();
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.edition = edition;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public List<BookIssue> getBookIssue() {
        return bookIssue;
    }

    public void setBookIssue(List<BookIssue> bookIssue) {
        this.bookIssue = bookIssue;
    }
}
