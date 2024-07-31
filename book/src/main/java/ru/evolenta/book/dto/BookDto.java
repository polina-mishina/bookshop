package ru.evolenta.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String title;
    private Integer authorId;
    private BigDecimal price;
    private Integer amount;
}
