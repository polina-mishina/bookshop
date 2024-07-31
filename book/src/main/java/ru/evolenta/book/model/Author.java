package ru.evolenta.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    @Id @GeneratedValue
    private int id;
    @NonNull
    private String firstname;
    private String surname;
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL) @JsonIgnore
    List<Book> books;

}
