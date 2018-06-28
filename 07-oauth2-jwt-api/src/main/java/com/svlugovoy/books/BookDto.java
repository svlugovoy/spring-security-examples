package com.svlugovoy.books;

import lombok.Data;

@Data
public class BookDto {

    private String author;
    private String name;
    private int year;

    Book convertToBook(){
        Book book = new Book();
        book.setAuthor(this.author);
        book.setName(this.name);
        book.setYear(this.year);
        return book;
    }
}
