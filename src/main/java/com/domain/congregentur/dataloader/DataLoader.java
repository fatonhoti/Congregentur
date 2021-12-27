package com.domain.congregentur.dataloader;

import java.util.List;

import com.domain.congregentur.book.Book;
import com.domain.congregentur.book.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private BookRepository bookRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void run(ApplicationArguments args) {
        // Create books here
        List<Book> default_books = List.of(
                new Book("In Search of Lost Time", "Marcel Proust", "9780812969641"),
                new Book("Swann\"s Way", "Marcel Proust", "9781772267143"),
                new Book("Don Quixote", "Miguel de Cervantes", "9780099469698"),
                new Book("1984", "George Orwell", "9780141036144"),
                new Book("To Kill A Mockingbird", "Harper Lee", "9780446310789"),
                new Book("The Book Thief", "Markus Zusak", "9781784162122"),
                new Book("The monk who sold his Ferrari", "Robin Sharma", "9780061125898"),
                new Book("The Alchemist", "Paulo Coelho", "9780062355300"));
        bookRepository.saveAll(default_books);
    }
}