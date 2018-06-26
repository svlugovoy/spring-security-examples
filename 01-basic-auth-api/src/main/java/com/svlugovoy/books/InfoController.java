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
@RequestMapping("/api/info")
public class InfoController {

    @GetMapping
    public List<String> getApiInfo() {
        return new ArrayList<String>() {{
            add("GET /api/books");
            add("GET /api/books/{id}");
            add("POST /api/books");
            add("PUT /api/books/{id}");
            add("DELETE /api/books/{id}");
        }};
    }



}
