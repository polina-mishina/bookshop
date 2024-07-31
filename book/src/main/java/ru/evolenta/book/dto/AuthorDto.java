package ru.evolenta.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDto {
    private String firstname;
    private String surname;
    private String lastname;
}
