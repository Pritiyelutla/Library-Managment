package com.acciojob.librarymanagementsystem.service;

import com.acciojob.librarymanagementsystem.Exception.InvalidPageCountException;
import com.acciojob.librarymanagementsystem.entities.Author;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.repository.AuthorRepository;
import com.acciojob.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service


public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception
    {
        if(book.getNoOfPages() <= 0)
        {
            throw new InvalidPageCountException("Page count entered is incorrect");
        }
        book.setIsIssued(Boolean.FALSE);
        bookRepository.save(book);
        return "book has been saved to db";
    }

    public String associateBookToAuthor(Integer bookId,Integer authorId) throws Exception
    {
        Optional<Book> bookoptional = bookRepository.findById(bookId);

        if (bookoptional.isEmpty())
        {
            throw new Exception("Book id entered is incorrect");
        }

        Book book = bookoptional.get();
        //Book book = bookRepository.findById(bookId).get();

        Optional<Author> optinalAuthor = authorRepository.findById(authorId);

        if(optinalAuthor.isEmpty())
        {
            throw new Exception("Author id entered is incorrect");
        }

        Author author = optinalAuthor.get();
       // Author author = authorRepository.findById(authorId).get();

        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);

        bookRepository.save(book);
        authorRepository.save(author);

        return "Associated book and author entity";
    }

    public List<Book> getBooksList(Integer authorId)
    {
        List<Book> allBooks = bookRepository.findAll();
        List<Book> ansList = new ArrayList<>();

        for(Book book : allBooks)
        {
            if(book.getAuthor().getAuthorID().equals(authorId))
            {
                ansList.add(book);
            }
        }
        return ansList;
    }

}
