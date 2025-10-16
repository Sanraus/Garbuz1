package com.example.demo.controller;

import com.example.demo.model.Reader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final List<Reader> readers = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Reader> getAll() {
        return readers;
    }

    @GetMapping("/{id}")
    public Reader getById(@PathVariable Long id) {
        return readers.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Reader create(@RequestBody Reader reader) {
        reader.setId(idGenerator.incrementAndGet());
        readers.add(reader);
        return reader;
    }

    @PutMapping("/{id}")
    public Reader update(@PathVariable Long id, @RequestBody Reader updated) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId().equals(id)) {
                updated.setId(id);
                readers.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        readers.removeIf(r -> r.getId().equals(id));
    }
}