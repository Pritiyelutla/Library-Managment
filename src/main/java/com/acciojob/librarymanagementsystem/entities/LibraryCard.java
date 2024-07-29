package com.acciojob.librarymanagementsystem.entities;

import com.acciojob.librarymanagementsystem.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Cardinfo")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    private Date validity;

    @JoinColumn 
    @OneToOne
    private Student student;

}
