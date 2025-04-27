package uk.ac.leedsbeckett.hmm.library_portal.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;
import uk.ac.leedsbeckett.hmm.library_portal.entities.LibraryAccount;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Transaction;
import uk.ac.leedsbeckett.hmm.library_portal.services.BookService;
import uk.ac.leedsbeckett.hmm.library_portal.services.LibraryAccountService;

import uk.ac.leedsbeckett.hmm.library_portal.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TransactionService transactionService;
    private final LibraryAccountService libraryAccountService;
    private final BookService bookService;

    public AdminController(TransactionService transactionService, LibraryAccountService libraryAccountService, BookService bookService) {
        this.transactionService = transactionService;
        this.libraryAccountService = libraryAccountService;
        this.bookService = bookService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getStudentsOverview() {
        // For each student: id, number of current loans, number of overdues
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/loans/current")
    public ResponseEntity<List<Transaction>> getAllCurrentLoans() {
        return new ResponseEntity<> (transactionService.getCurrentLoans(),HttpStatus.OK);
    }

    @GetMapping("/loans/overdue")
    public ResponseEntity<List<Transaction>> getAllOverdueLoans() {
        return new ResponseEntity<> (transactionService.getOverdueLoans(),HttpStatus.OK);
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<> (bookService.addBook(book),HttpStatus.CREATED);
    }

}
