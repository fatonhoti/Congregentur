package com.domain.congregentur.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

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
        Book book = bookService.findById(id);
        if (book == null) {
            // Book with supplied id was not found!!!
            model.addAttribute("books", bookService.findAll());
        } else {
            model.addAttribute("books", List.of(book));
        }
        return "books";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "create";
    }

    @PostMapping("/create")
    public View createBook(@ModelAttribute("book") Book book, Model model) {
        Book newBook = bookService.createBook(book);
        if (newBook == null) {
            // Book creation failed!!!
            // // TODO: Handle appropriately.
        }
        model.addAttribute("books", bookService.findAll());
        return new RedirectView("/api/books");
    }

    @PutMapping("{id}")
    public View updateBook(@PathVariable("id") Long id, @RequestBody Book updatedBook, Model model) {
        Book book = bookService.updateBook(id, updatedBook);
        if (book == null) {
            // Book update failed!!!
            model.addAttribute("books", bookService.findAll());
        } else {
            model.addAttribute("books", List.of(book));
        }
        return new RedirectView("/api/books");
    }

    @DeleteMapping("/{id}")
    public View deleteBook(@PathVariable("id") Long id, Model model) {
        boolean removed = bookService.deleteBook(id);
        if (!removed) {
            // Book removal failed!!!
            // TODO: Handle appropriately.
        }
        model.addAttribute("books", bookService.findAll());
        return new RedirectView("/api/books");
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            // Book with supplied id was not found!!!
            // Create HttpStatus and ResponseEntity?
            return "books";
        } else {
            model.addAttribute("book", book);
            return "edit";
        }
    }

    @PostMapping("/edit/{id}")
    public View editBook(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("isbn") String isbn, Model model) {
        if (!title.isEmpty() && !author.isEmpty() && (!isbn.isEmpty() && isbn.length() == 13))
            bookService.updateBook(id, new Book(title, author, isbn));
        model.addAttribute("books", bookService.findAll());
        return new RedirectView("/api/books");
    }

    @GetMapping("/search")
    public String findByTitleOrAuthorOrIsbn(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("books", bookService.findByTitleOrAuthorOrIsbn(keyword));
        return "books";
    }

}
