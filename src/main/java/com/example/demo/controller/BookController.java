package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        book.setId(counter.incrementAndGet());
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book updated) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updated.setId(id);
                books.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }
}