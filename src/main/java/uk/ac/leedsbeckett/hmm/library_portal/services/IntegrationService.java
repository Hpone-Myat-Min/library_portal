package uk.ac.leedsbeckett.hmm.library_portal.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.ac.leedsbeckett.hmm.library_portal.entities.FinanceAccount;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Fine;

@Component
public class IntegrationService {
    // To call other APIs

    private final RestTemplate restTemplate;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FinanceAccount getFinanceAccount(String studentId ) {
        return restTemplate.getForObject("http://localhost:8081/accounts/student/" + studentId, FinanceAccount.class);
    }

    public Fine createLibraryFineInvoice(Fine fine ) {
        // Issue Library Fine when return late
        return restTemplate.postForObject("http://localhost:8081/invoices/", fine, Fine.class);
    }

    public Fine getInvoice(String reference){
        return restTemplate.getForObject("http://localhost:8081/invoices/reference/" + reference, Fine.class);
    }

}
