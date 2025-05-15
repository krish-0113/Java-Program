import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
package org.book.application.bookapplication.entity;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId; 

    private String authorName; 
    private String language;

    @OneToOne(mappedBy = "author") // maintain reln
    @JsonBackReference
    private Book book;

    // Default Constructor
    public Author() {
    }

    // Parameterized Constructor
    public Author(int authorId, String authorName, String language) {
        this.authorId = authorId;
        this.authorName = authorName; 
        this.language = language;
    }

    // Getters and Setters
    public Book getBook() {  // Changed return type to Book
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
