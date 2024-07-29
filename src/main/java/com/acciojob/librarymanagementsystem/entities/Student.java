package com.acciojob.librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity

@Table(name =  "StudentInfo")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roll;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private double cgpa;

    private String branch;

    @NotNull
    @Column(unique = true)
    private String emailID;

}
