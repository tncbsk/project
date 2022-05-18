package com.getir.readingisgood.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode
@Document(collection = "book")
public class Book  implements BaseEntity{

    @Id
    private String id;

    private String isbn;

    private String bookName;

    private String writer;

    private String existYear;

    private Long stock;

    private double price;

}
