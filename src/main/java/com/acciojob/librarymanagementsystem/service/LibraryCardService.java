package com.acciojob.librarymanagementsystem.service;

import com.acciojob.librarymanagementsystem.CardStatus;
import com.acciojob.librarymanagementsystem.entities.LibraryCard;
import com.acciojob.librarymanagementsystem.entities.Student;
import com.acciojob.librarymanagementsystem.repository.LibraryCardRepository;
import com.acciojob.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class LibraryCardService {

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generatedCard()
    {
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBooksIssued(0);

        Date exp = new Date(128,6,1);
        card.setValidity(exp);

        card =  libraryCardRepository.save(card);
        return "The card has been added to db"+card.getCardNo();
    }

    public String associateCardAndStudent(Integer cardID, Integer studentID)
    {
        LibraryCard libraryCard = libraryCardRepository.findById(cardID).get();

        Student student = studentRepository.findById(studentID).get();

        libraryCard.setCardStatus(CardStatus.ISSUED);

        libraryCard.setStudent(student);

        libraryCardRepository.save(libraryCard);

        return "the card and student has been associated";
    }

}
