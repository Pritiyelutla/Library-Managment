package com.acciojob.librarymanagementsystem.repository;

import com.acciojob.librarymanagementsystem.TransactionStatus;
import com.acciojob.librarymanagementsystem.entities.Book;
import com.acciojob.librarymanagementsystem.entities.LibraryCard;
import com.acciojob.librarymanagementsystem.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {


    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);


}
