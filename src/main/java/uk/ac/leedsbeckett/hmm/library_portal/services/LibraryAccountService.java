package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.LibraryAccount;

public interface LibraryAccountService {

    LibraryAccount createAccount( String studentId);
    LibraryAccount updateAccount( String studentId, LibraryAccount updatedAccount);
    LibraryAccount getAccount( String studentId );

}
