package com.example.demo.controller;

import com.example.demo.model.Author;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final List<Author> authors = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Author> getAll() {
        return authors;
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return authors.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        author.setId(idGenerator.incrementAndGet());
        authors.add(author);
        return author;
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author updated) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId().equals(id)) {
                updated.setId(id);
                authors.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authors.removeIf(a -> a.getId().equals(id));
    }
}