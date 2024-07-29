package com.acciojob.librarymanagementsystem.repository;

import com.acciojob.librarymanagementsystem.entities.LibraryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LibraryCardRepository extends JpaRepository <LibraryCard,Integer>{



}
