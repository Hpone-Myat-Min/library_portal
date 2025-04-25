package uk.ac.leedsbeckett.hmm.library_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int copies;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private List<Transaction> transactions = new ArrayList<>();

}
