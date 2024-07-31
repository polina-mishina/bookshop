package ru.evolenta.book.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.evolenta.book.dto.AuthorDto;
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

    public ResponseEntity<Author> save(AuthorDto authorDto) {
        Author author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return new ResponseEntity<>(repository.save(author), HttpStatus.CREATED);
    }

    public ResponseEntity<Author> update(int id, AuthorDto authorDto) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Author author = optionalAuthor.get();
        BeanUtils.copyProperties(authorDto, author);
        return new ResponseEntity<>(repository.save(author), HttpStatus.OK);
    }

    public ResponseEntity<Author> delete(int id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Author author = optionalAuthor.get();
        repository.deleteById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
