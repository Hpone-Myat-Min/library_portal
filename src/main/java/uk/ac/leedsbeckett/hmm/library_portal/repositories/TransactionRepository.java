package uk.ac.leedsbeckett.hmm.library_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByStudentId(String studentId);
    List<Transaction> findByStudentIdAndDateReturnedIsNull(String studentId);
    List<Transaction> findByDateReturnedIsNull();

    Transaction findByBook_IsbnAndStudentIdAndDateReturnedIsNull(String bookIsbn, String studentId);
}
