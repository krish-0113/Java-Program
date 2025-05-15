import org.book.application.bookapplication.entity.Book;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String title;

    @OneToOne
    @JoinColumn(name = "author_id") // Defines foreign key in Book table
    @JsonManagedReference
    private Author author;

    // Default Constructor
    public Book() {
    }

    // Parameterized Constructor
    public Book(int id, String title, Author author) {
        this.id = id;
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

    public Author getAuthor() {  // Changed return type to Author
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
