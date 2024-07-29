package com.acciojob.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table
@Entity

@Setter
@Getter

public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer authorID;

    private String name;

    private Integer age;

    @NotNull
    @Column(unique = true)
    private String emailId;

    private Double rating;

    private int noOfBooks;
}