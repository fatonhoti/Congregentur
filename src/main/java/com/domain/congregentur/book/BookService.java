package com.domain.congregentur.book;

import com.domain.congregentur.util.UtilityFunctions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByTitleOrAuthorOrIsbn(String keyword) {
        return bookRepository.findByTitleOrAuthorOrIsbn(keyword);
    }

    // Read (all)
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        Collections.sort(books, Comparator.comparingLong(Book::getId));
        return books;
    }

    // Read (single)
    public Book findById(Long id) {
        try {
            Book b = bookRepository.findById(id).get();
            return b;
        } catch (NoSuchElementException e) {
            // Supplied id does not exist.
            return null;
        }
    }

    // Create
    public Book createBook(Book book) {
        if (UtilityFunctions.bookIsValid(book)) {
            return bookRepository.save(book);
        }
        return null;
    }

    // Update
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            if (UtilityFunctions.bookIsValid(oldBook.get())) {
                Book updated = oldBook.get().updateWith(updatedBook);
                return bookRepository.save(updated);
            }
        }
        return null;
    }

    // Delete
    public boolean deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Supplied id does not exist.
            return false;
        }
        return true;
    }

}
