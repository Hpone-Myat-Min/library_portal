package uk.ac.leedsbeckett.hmm.library_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
