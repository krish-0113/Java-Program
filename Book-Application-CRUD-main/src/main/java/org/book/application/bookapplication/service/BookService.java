package org.book.application.bookapplication.service;

import org.book.application.bookapplication.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Book getBook(int id);
    Book updateBook(int id, Book book);
    void deleteBook(int id);
}
