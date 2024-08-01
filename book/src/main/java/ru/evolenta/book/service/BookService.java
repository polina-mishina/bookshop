package ru.evolenta.book.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.evolenta.book.dto.BookRequest;
import ru.evolenta.book.model.Author;
import ru.evolenta.book.model.Book;
import ru.evolenta.book.repository.AuthorRepository;
import ru.evolenta.book.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<Book> findById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Book> save(BookRequest bookRequest) {
        Optional<Author> optionalAuthor = authorRepository.findById(bookRequest.getAuthorId());
        if (optionalAuthor.isEmpty())
            return ResponseEntity.badRequest().build();
        Author author = optionalAuthor.get();
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book, "authorId");
        book.setAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
    }

    public ResponseEntity<Book> update(int id, BookRequest bookRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty())
            return ResponseEntity.notFound().build();
        Optional<Author> optionalAuthor = authorRepository.findById(bookRequest.getAuthorId());
        if (optionalAuthor.isEmpty())
            return ResponseEntity.notFound().build();
        Author author = optionalAuthor.get();
        Book book = optionalBook.get();
        BeanUtils.copyProperties(bookRequest, book, "authorId");
        book.setAuthor(author);
        return ResponseEntity.ok(bookRepository.save(book));
    }

    public ResponseEntity<Book> delete(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            return ResponseEntity.notFound().build();
        Book book = bookOptional.get();
        bookRepository.deleteById(id);
        return ResponseEntity.ok(book);
    }
}
