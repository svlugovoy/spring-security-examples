package com.svlugovoy.books;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="BOOK")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOK_ID")
    private Long id;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="NAME")
    private String name;
    @Column(name="YEAR")
    private int year;

}
