package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote extends BaseEntity {

    private String text;
    private String author;
    private String reason;
    private String place;
    private int year;

    public Quote() {
    }

    @Column(name = "quote_text",nullable = false, unique = true, updatable = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "quote_author",nullable = false, unique = true, updatable = true)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "quote_reason",nullable = true, unique = true, updatable = true)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "quote_place",nullable = true, unique = true, updatable = true)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "quote_year",nullable = true, unique = true, updatable = true)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
