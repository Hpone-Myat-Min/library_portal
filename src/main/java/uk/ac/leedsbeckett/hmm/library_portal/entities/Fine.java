package uk.ac.leedsbeckett.hmm.library_portal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Fine {
    // Data Transfer Object to map Invoice return from Finance Portal as Library Fine

    private Double amount;
    private String reference;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private String type;
    private String status;
    private String studentId;
    private FinanceAccount account;

}



