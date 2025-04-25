package uk.ac.leedsbeckett.hmm.library_portal.services;

import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.hmm.library_portal.entities.LibraryAccount;
import uk.ac.leedsbeckett.hmm.library_portal.repositories.LibraryAccountRepository;

@Service
public class LibraryAccountServiceImpl implements LibraryAccountService{

    private final LibraryAccountRepository libraryAccountRepository;

    public LibraryAccountServiceImpl( LibraryAccountRepository libraryAccountRepository ) {
        this.libraryAccountRepository = libraryAccountRepository;
    }

    @Override
    public LibraryAccount createAccount(LibraryAccount account) {

        LibraryAccount newAccount = new LibraryAccount();
        newAccount.setStudentId(account.getStudentId());
        newAccount.setPin("000000");
        newAccount.setUpdated(false);
        return libraryAccountRepository.save(newAccount);
    }

    @Override
    public LibraryAccount updateAccount(String studentId, LibraryAccount updatedAccount) {

        LibraryAccount currentAccount = libraryAccountRepository.findByStudentId(studentId).orElse(null);
        if ( currentAccount != null ) {
            throw new RuntimeException("Library account with id " + studentId + " not found.");
        }

        currentAccount.setPin(updatedAccount.getPin());
        return libraryAccountRepository.save(currentAccount);
    }

    @Override
    public LibraryAccount getAccount(String studentId) {
        return libraryAccountRepository.findByStudentId(studentId).orElse(null);
    }
}
