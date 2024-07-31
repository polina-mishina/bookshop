package ru.evolenta.book.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id @GeneratedValue
    private int id;
    @NonNull
    private String title;
    @ManyToOne
    private Author author;
    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.valueOf(0.0);
    private Integer amount = 0;

    public Book(@NonNull String title, @NonNull Author author, @NonNull BigDecimal price, @NonNull Integer amount) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.amount = amount;
    }
}
