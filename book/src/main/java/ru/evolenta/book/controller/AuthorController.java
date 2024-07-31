package ru.evolenta.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evolenta.book.dto.AuthorDto;
import ru.evolenta.book.model.Author;
import ru.evolenta.book.repository.AuthorRepository;
import ru.evolenta.book.service.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorService service;

    @GetMapping
    public Iterable<Author> findAll() {
        return repository.findAllByOrderBySurnameAscFirstnameAscLastnameAsc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return service.save(authorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable int id, @RequestBody AuthorDto authorDto) {
        return service.update(id, authorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable int id) {
        return service.delete(id);
    }

}
