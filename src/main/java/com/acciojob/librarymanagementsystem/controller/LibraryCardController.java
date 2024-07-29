package com.acciojob.librarymanagementsystem.controller;

import com.acciojob.librarymanagementsystem.repository.LibraryCardRepository;
import com.acciojob.librarymanagementsystem.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")

public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

    @PostMapping("/generateCard")
    public ResponseEntity addCard()
    {
        String result = libraryCardService.generatedCard();
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("cardID")Integer cardID,
                                                  @RequestParam("studentID")Integer studentID)
    {
        String result = libraryCardService.associateCardAndStudent(cardID,studentID);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
