package uk.ac.leedsbeckett.hmm.library_portal.entities;
import lombok.Data;

@Data
public class StudentSummary {

    private int totalLoans;
    private int totalOverdues;

    public StudentSummary(int totalLoans, int totalOverdues) {
    }
}
