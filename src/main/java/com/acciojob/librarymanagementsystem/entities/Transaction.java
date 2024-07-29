package com.acciojob.librarymanagementsystem.entities;

import com.acciojob.librarymanagementsystem.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table

@Getter
@Setter

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String TransactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date issueDate;

    private Date returnDate;

    private Integer fineAmount;

    @JoinColumn
    @ManyToOne
    private LibraryCard libraryCard;

    @JoinColumn
    @ManyToOne
    private Book book;

}
