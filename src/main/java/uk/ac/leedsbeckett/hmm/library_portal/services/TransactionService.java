package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction borrowBook(String bookIsbn, String studentId);
    Transaction returnBook(String bookIsbn, String studentId);
    Transaction getTransaction(Long transactionId);
    List<Transaction> getAllTransactions();
    List<Transaction> getStudentTransaction(String studentId);
    List<Transaction> getCurrentLoans();
    List<Transaction> getOverdueLoans();
}
