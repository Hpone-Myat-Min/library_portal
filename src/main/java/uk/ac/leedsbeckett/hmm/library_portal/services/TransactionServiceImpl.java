package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Transaction;
import uk.ac.leedsbeckett.hmm.library_portal.repositories.BookRepository;
import uk.ac.leedsbeckett.hmm.library_portal.repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;

    public TransactionServiceImpl( TransactionRepository transactionRepository, BookRepository bookRepository ) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Transaction borrowBook(String bookIsbn, String studentId) {

        Book book = bookRepository.findById(bookIsbn).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getCopies() == 0) {
            throw new RuntimeException("No copies available");
        }

        book.setCopies(book.getCopies() - 1);
        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setStudentId(studentId);
        transaction.setBook(book);
        transaction.setDateBorrowed(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(14));

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction returnBook(String bookIsbn, String studentId) {
        Book book = bookRepository.findById(bookIsbn).orElseThrow(() -> new RuntimeException("Book not found"));
        Transaction studentTransaction = transactionRepository.findByBook_IsbnAndStudentIdAndDateReturnedIsNull(bookIsbn, studentId);
        if (studentTransaction == null) {
            throw new RuntimeException("No active loan for this book: " + bookIsbn);
        }

        if(LocalDate.now().isAfter(studentTransaction.getDueDate())) {
            // ISSUE FINE
        }

        studentTransaction.setDateReturned(LocalDate.now());
        transactionRepository.save(studentTransaction);

        book.setCopies(book.getCopies() + 1);
        bookRepository.save(book);

        return studentTransaction;
    }

    @Override
    public Optional<Transaction> getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getStudentTransaction(String studentId) {
        return transactionRepository.findByStudentId(studentId);
    }

    @Override
    public List<Transaction> getCurrentLoans() {
        return transactionRepository.findByDateReturnedIsNull();
    }

    @Override
    public List<Transaction> getOverdueLoans() {

        List<Transaction> overdueTransactions = new ArrayList<>();

        List<Transaction> currentLoans = getCurrentLoans();
        for (Transaction transaction : currentLoans) {
            if(LocalDate.now().isAfter(transaction.getDueDate())) {
                overdueTransactions.add(transaction);
            }
        }
        return overdueTransactions;
    }
}
