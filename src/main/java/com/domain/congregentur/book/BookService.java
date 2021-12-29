package com.domain.congregentur.book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Read (all)
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        Collections.sort(books, Comparator.comparingLong(Book::getId));
        return books;
    }

    // Read (single)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // Create
    public Book createBook(Book book) {
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            return null;
        }
        if (!book.getIsbn().isEmpty() && book.getIsbn().length() == 13) {
            return bookRepository.save(book);
        }
        return null;
    }

    // Update
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            // Verify contents of updatedBook
            if (!updatedBook.getTitle().isEmpty() &&
                    !updatedBook.getAuthor().isEmpty() &&
                    !updatedBook.getIsbn().isEmpty() &&
                    updatedBook.getIsbn().length() == 13) {
                Book updated = oldBook.get().updateWith(updatedBook);
                return bookRepository.save(updated);
            }
        }
        return null;
    }

    // Delete
    public void deleteBook(Long id) {
        // TODO: Put this in a try, catch
        // since 'deleteById' throws an "EmptyResultDataAccessException"
        // when supplied id does not exist.
        bookRepository.deleteById(id);
    }

}
