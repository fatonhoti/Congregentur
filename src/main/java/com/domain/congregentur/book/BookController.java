package com.domain.congregentur.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("books", List.of(book.get()));
        }
        return "books";
    }

    @PostMapping
    public String createBook(@RequestBody Book book, Model model) {
        Book newBook = bookService.createBook(book);
        if (newBook != null) {
            model.addAttribute("books", List.of(newBook));
        }
        return "books";
    }

    @PutMapping("{id}")
    public String updateBook(@PathVariable("id") Long id, @RequestBody Book updatedBook, Model model) {
        Book book = bookService.updateBook(id, updatedBook);
        if (book == null) {
            // Book update failed!!!
            // Create HttpStatus and ResponseEntity?
        }
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "books";
    }

}
