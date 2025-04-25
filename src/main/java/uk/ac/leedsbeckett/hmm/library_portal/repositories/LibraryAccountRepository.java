package uk.ac.leedsbeckett.hmm.library_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.library_portal.entities.LibraryAccount;

import java.util.Optional;

public interface LibraryAccountRepository extends JpaRepository<LibraryAccount, Long> {

    Optional<LibraryAccount> findByStudentId(String studentId );
    
}
