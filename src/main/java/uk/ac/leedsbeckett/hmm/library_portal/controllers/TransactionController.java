package uk.ac.leedsbeckett.hmm.library_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Fine;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Transaction;
import uk.ac.leedsbeckett.hmm.library_portal.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class TransactionController {
    // Represent the borrowing transactions in library portal

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        // Constructor Injection
        this.transactionService = transactionService;
    }

    @PutMapping("{studentId}/borrow/{bookIsbn}")
    public ResponseEntity<Transaction> borrowBook(@PathVariable String studentId, @PathVariable String bookIsbn) {
        Transaction borrowTransaction = transactionService.borrowBook(bookIsbn, studentId);
        return new ResponseEntity<>( borrowTransaction, HttpStatus.OK);
    }

    @PutMapping("{studentId}/return/{bookIsbn}")
    public ResponseEntity<Fine> returnBook(@PathVariable String studentId, @PathVariable String bookIsbn) {
        Fine fine = transactionService.returnBook(bookIsbn, studentId);
        return new ResponseEntity<>(fine, HttpStatus.OK);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId){
        Transaction transaction = transactionService.getTransaction(transactionId);
        return new ResponseEntity<>( transaction, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getTransactions(){
        List<Transaction> transactionList = transactionService.getAllTransactions();
        return new ResponseEntity<>( transactionList,HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Transaction>> getStudentTransactions(@PathVariable String studentId){
        List<Transaction> studentTransactions = transactionService.getStudentTransaction(studentId);
        return new ResponseEntity<>( studentTransactions,HttpStatus.OK);
    }

    @GetMapping("/current/")
    public ResponseEntity<List<Transaction>> getCurrentLoans(){
        List<Transaction> currentLoans = transactionService.getCurrentLoans();
        return new ResponseEntity<>( currentLoans,HttpStatus.OK);
    }

    @GetMapping("/overdue/")
    public ResponseEntity<List<Transaction>> getOverdueLoans(){
        List<Transaction> overdueLoans = transactionService.getOverdueLoans();
        return new ResponseEntity<>( overdueLoans,HttpStatus.OK);
    }

}
