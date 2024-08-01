package ru.evolenta.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.evolenta.book.dto.BookRequest;
import ru.evolenta.book.model.Book;
import ru.evolenta.book.repository.BookRepository;
import ru.evolenta.book.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookService service;

    @GetMapping
    public Iterable<Book> findAll() {
        return repository.findAllByOrderByTitleAsc();
    }

    @GetMapping(params = "title")
    public Iterable<Book> findByTitle(@RequestParam String title) {
        return repository.findAllByTitleOrderByTitleAsc(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookRequest bookRequest) {
        return service.save(bookRequest);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody BookRequest bookRequest) {
        return service.update(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable int id) {
        return service.delete(id);
    }
}
