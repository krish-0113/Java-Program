package org.book.application.bookapplication.service;

import org.book.application.bookapplication.entity.Book;
import org.book.application.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add a book
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null); // Return null if not found
    }

    // Update the book
    @Override
    public Book updateBook(int id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookDetails.getTitle());  // Update title
            existingBook.setAuthor(bookDetails.getAuthor());  // Update author
            return bookRepository.save(existingBook);
        }
        return null; // Return null if book not found
    }

    // Delete book by ID
    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
