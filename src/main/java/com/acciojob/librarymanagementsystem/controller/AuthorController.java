package com.acciojob.librarymanagementsystem.controller;

import com.acciojob.librarymanagementsystem.entities.Author;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/author")

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author)
    {
        String result = authorService.addAuthor(author);
        return result;
    }


    @GetMapping("/getAuthorWithMaxBooks")
    public Author getAuthorWithMaxBooks()
    {
        return authorService.getAuthorWithMaxBooks();
    }
}
