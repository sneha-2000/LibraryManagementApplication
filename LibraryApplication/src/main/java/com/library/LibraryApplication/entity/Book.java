package com.library.LibraryApplication.entity;

import javax.persistence.*;

@Entity
@Table(name="BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long bookId;

    private String title;
    private String author;
    private int releaseYear;
    private int edition;

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

}
