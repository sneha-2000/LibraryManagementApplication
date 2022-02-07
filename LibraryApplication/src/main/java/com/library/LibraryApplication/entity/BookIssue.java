package com.library.LibraryApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookIssue {
    @Id
    private Long id;
    @Column(nullable = false)
    private String issueDate;
    @Column(nullable = false)
    private String returnDate;
    @Column(nullable = false)
    private double price;

    @JsonIgnore
    @ManyToOne
    private Book book;

    public BookIssue(){}

    public BookIssue(Book book,String issueDate,double price,String returnDate){
        this.book=book;
        this.issueDate=issueDate;
        this.price=price;
        this.returnDate=returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
