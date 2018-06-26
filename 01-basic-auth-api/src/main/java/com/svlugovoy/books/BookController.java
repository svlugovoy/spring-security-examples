package com.svlugovoy.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return new ArrayList<>(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        Book book = this.repository.save(bookDto.convertToBook());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(location).body(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        Optional<Book> book = this.repository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        Optional<Book> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new BookNotFoundException("Bookt not found with id: " + id);
        }
        Book book = bookDto.convertToBook();
        book.setId(id);
        return this.repository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteBook(@PathVariable Long id){
        Optional<Book> existing = this.repository.findById(id);
        if(!existing.isPresent()){
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        this.repository.deleteById(id);
    }
}
