package uk.ac.leedsbeckett.hmm.library_portal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int copies;

}
