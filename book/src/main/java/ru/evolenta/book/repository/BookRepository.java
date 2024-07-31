package ru.evolenta.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evolenta.book.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Iterable<Book> findAllByOrderByTitleAsc();
    Iterable<Book> findAllByTitleOrderByTitleAsc(String title);
}
