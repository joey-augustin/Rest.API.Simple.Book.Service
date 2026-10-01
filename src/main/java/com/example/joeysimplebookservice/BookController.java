package com.example.joeysimplebookservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();
    private int nextId = 4;

    public BookController() {
        books.add(new Book(
                1,
                "The Hobbit",
                "J.R.R Tolkien",
                14.99,
                "Fantasy"
        ));

        books.add(new Book(
                2,
                "Dune",
                "Frank Herbert",
                16.99,
                "Science Fiction"
        ));

        books.add(new Book(
                3,
                "1984",
                "George Orwell",
                12.99,
                "Dystopia"
        ));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return ResponseEntity.status(201).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                return ResponseEntity.status(204).build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updateBook) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());
                book.setPrice(updateBook.getPrice());
                book.setGenre(updateBook.getGenre());

                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }
}