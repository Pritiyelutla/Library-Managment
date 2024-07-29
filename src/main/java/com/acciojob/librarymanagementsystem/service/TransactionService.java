package com.acciojob.librarymanagementsystem.service;

import com.acciojob.librarymanagementsystem.TransactionStatus;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.entities.LibraryCard;
import com.acciojob.librarymanagementsystem.entities.Transaction;
import com.acciojob.librarymanagementsystem.repository.BookRepository;
import com.acciojob.librarymanagementsystem.repository.LibraryCardRepository;
import com.acciojob.librarymanagementsystem.repository.TransactionRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service



public class TransactionService{


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static Integer MaxNoOfIssuedBooks=3;

    public static final Integer FINE_PER_DAY = 5;
    public String issueBook (Integer cardId, Integer bookId) throws Exception
    {
         //1. book and card should be valid

        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(bookOptional.isEmpty())
        {
            throw new Exception("Book id entered is incorrect");
        }
        Book book = bookOptional.get();

        //LibraryCard card = libraryCardRepository.findById(cardId).get();
        //Book book = bookRepository.findById(bookId).get();

        //2.library card should be valid
        Optional<LibraryCard> cardOptional = libraryCardRepository.findById(cardId);
        if(cardOptional.isEmpty())
        {
            throw new Exception("Card id entered is invalid");
        }
        LibraryCard card = cardOptional.get();

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //3.book should not be issued
        if (book.getIsIssued())
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Book is already issued to cardid "+cardId;
        }

        //4.limit of a card exceeded
        if(card.getNoOfBooksIssued()>MaxNoOfIssuedBooks)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Max limit of this card has exceeded";
        }


        // 5. check for if the card has expired its validity

        Long timeOfCardValidity = card.getValidity().getTime();
        Long currTime = System.currentTimeMillis();

        if(currTime > timeOfCardValidity)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Card has been expired";
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);


        bookRepository.save(book);
        libraryCardRepository.save(card);
        transaction = transactionRepository.save(transaction);

        return "The transaction has been completed with transactionId"+transaction.getTransactionId();

    }

    public String returnBook(Integer cardId,Integer bookId)
    {

        //Need to find transaction with cardid , bookid and issued status

        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = libraryCardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        //fine amount to be calculated

        Long timediffInMS = System.currentTimeMillis() - transaction.getIssueDate().getTime();

        Long days = TimeUnit.DAYS.convert(timediffInMS,TimeUnit.MILLISECONDS);

        Integer fineAmt = 0 ;

        if(days > 15)
            fineAmt = (Integer) Math.toIntExact((days - 15)*FINE_PER_DAY);

        //save the transaction

        transaction.setFineAmount(fineAmt);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());
        book.setIsIssued(false);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transactionRepository.save(transaction);
        bookRepository.save(book);
        libraryCardRepository.save(card);

        return "The book has been successfully returned";
    }
}
