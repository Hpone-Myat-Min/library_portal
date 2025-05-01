package uk.ac.leedsbeckett.hmm.library_portal.entities;
import lombok.Data;

@Data
public class StudentSummary {
    // Class to simplify representing loans and overdues in Admin Portal

    private int totalLoans;
    private int totalOverdues;

    public StudentSummary(int totalLoans, int totalOverdues) {
    }
}
