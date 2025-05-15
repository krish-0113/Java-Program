package org.book.application.bookapplication.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)  // Ensures that Author is saved with Book
    @JoinColumn(name = "author_id", referencedColumnName = "authorId")
    @JsonManagedReference
    private Author author;

    // Default Constructor
    public Book() {
    }

    // Parameterized Constructor
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
