package com.acciojob.librarymanagementsystem.controller;

import com.acciojob.librarymanagementsystem.entities.Author;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.service.AuthorService;
import com.acciojob.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")

public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book)
    {
        try{
            String result = bookService.addBook(book);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/associateBookAndAuthor")
    public String associateBookAndAuthor(@RequestParam("bookID")Integer bookId,
                                         @RequestParam("authorID")Integer authorId)
    {
        try{
            return bookService.associateBookToAuthor(bookId,authorId);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getBookListByAuthor")
    public List<Book> getBookList(@RequestParam("authorId")Integer authorId)
    {
        return bookService.getBooksList(authorId);
    }
}
