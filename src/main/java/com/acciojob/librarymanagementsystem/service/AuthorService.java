package com.acciojob.librarymanagementsystem.service;

import com.acciojob.librarymanagementsystem.entities.Author;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service

public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author)
    {
        author.setNoOfBooks(0);
        authorRepository.save(author);
        return "Author has been saved to the db";
    }

    public Author getAuthorWithMaxBooks()
    {
        List<Author> authorList = authorRepository.findAll();

        Author ansAuthor = null;
        int max = 0;

        for(Author author : authorList)
        {
            if(author.getNoOfBooks() > max)
            {
                max = author.getNoOfBooks();
                ansAuthor = author;
            }
        }
        return ansAuthor;
    }
}
