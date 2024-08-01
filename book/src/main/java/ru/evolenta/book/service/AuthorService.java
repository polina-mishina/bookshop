package ru.evolenta.book.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.evolenta.book.dto.AuthorRequest;
import ru.evolenta.book.model.Author;
import ru.evolenta.book.repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public ResponseEntity<Author> findById(int id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        return optionalAuthor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Author> save(AuthorRequest authorRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(authorRequest, author);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(author));
    }

    public ResponseEntity<Author> update(int id, AuthorRequest authorRequest) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isEmpty())
            return ResponseEntity.notFound().build();
        Author author = optionalAuthor.get();
        BeanUtils.copyProperties(authorRequest, author);
        return ResponseEntity.ok(repository.save(author));
    }

    public ResponseEntity<Author> delete(int id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isEmpty())
            return ResponseEntity.notFound().build();
        Author author = optionalAuthor.get();
        repository.deleteById(id);
        return ResponseEntity.ok(author);
    }
}
