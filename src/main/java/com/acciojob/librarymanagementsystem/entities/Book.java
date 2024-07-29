
package com.acciojob.librarymanagementsystem.entities;

import com.acciojob.librarymanagementsystem.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.Boolean;

@Entity
@Table

@Getter
@Setter


public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BookID;

    @Column(unique = true)
    private String Title;

    private Integer noOfPages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Integer price;

    private Boolean isIssued;

    @JoinColumn
    @ManyToOne
    private Author author;

}
