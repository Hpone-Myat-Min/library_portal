package uk.ac.leedsbeckett.hmm.library_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class FinanceAccount {

    private String studentId;

}
