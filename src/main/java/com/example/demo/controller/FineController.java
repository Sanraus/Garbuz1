package com.example.demo.controller;

import com.example.demo.model.Fine;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final List<Fine> fines = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public List<Fine> getAll() {
        return fines;
    }

    @GetMapping("/{id}")
    public Fine getById(@PathVariable Long id) {
        return fines.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Fine create(@RequestBody Fine fine) {
        fine.setId(idGenerator.incrementAndGet());
        fines.add(fine);
        return fine;
    }

    @PutMapping("/{id}")
    public Fine update(@PathVariable Long id, @RequestBody Fine updated) {
        for (int i = 0; i < fines.size(); i++) {
            if (fines.get(i).getId().equals(id)) {
                updated.setId(id);
                fines.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fines.removeIf(f -> f.getId().equals(id));
    }
}