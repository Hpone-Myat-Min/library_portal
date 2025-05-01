package uk.ac.leedsbeckett.hmm.library_portal.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LibraryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;

    private String pin;

}
