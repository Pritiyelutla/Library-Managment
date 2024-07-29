package com.acciojob.librarymanagementsystem.repository;

import com.acciojob.librarymanagementsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
