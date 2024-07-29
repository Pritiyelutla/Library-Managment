package com.acciojob.librarymanagementsystem.controller;

import com.acciojob.librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/issueBook")
    public String issueBook(@RequestParam("cardId")Integer cardId,
                            @RequestParam("bookId")Integer bookId)
    {
        try {
            String result = transactionService.issueBook(cardId, bookId);
            return result;
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/returnBook")
    public String returnBook(@RequestParam("cardId")Integer cardId,
                             @RequestParam("bookId")Integer bookId
                             )
    {

        return transactionService.returnBook(cardId,bookId);
    }
}
