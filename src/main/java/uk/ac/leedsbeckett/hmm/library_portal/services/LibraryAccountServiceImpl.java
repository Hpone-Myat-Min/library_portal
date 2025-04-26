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
    public LibraryAccount loginAccount(String studentId, String pin) {
        LibraryAccount libraryAccount = libraryAccountRepository.findByStudentId(studentId).orElseThrow(() -> new RuntimeException("Library Account not found with studentId: " + studentId));
        if (!libraryAccount.getPin().equals(pin)) {
            throw new RuntimeException("Invalid username or password");
        }
        return libraryAccount;
    }

    @Override
    public LibraryAccount loginAdmin(String pin) {
        LibraryAccount libraryAccount = libraryAccountRepository.findByStudentId("admin").orElseThrow(() -> new RuntimeException("Admin account not found"));
        if (!libraryAccount.getPin().equals(pin)) {
            throw new RuntimeException("Invalid pin number");
        }
        return libraryAccount;
    }

    @Override
    public LibraryAccount createAccount(LibraryAccount libraryAccount) {

        LibraryAccount newAccount = new LibraryAccount();
        newAccount.setStudentId(libraryAccount.getStudentId());
        newAccount.setPin("000000");
        newAccount.setUpdated(false);
        return libraryAccountRepository.save(newAccount);
    }

    @Override
    public LibraryAccount updateAccount(String studentId, LibraryAccount updatedAccount) {

        LibraryAccount currentAccount = libraryAccountRepository.findByStudentId(studentId).orElse(null);
        if ( currentAccount == null ) {
            throw new RuntimeException("Library account with id " + studentId + " not found.");
        }

        currentAccount.setPin(updatedAccount.getPin());
        currentAccount.setUpdated(true);
        return libraryAccountRepository.save(currentAccount);
    }

    @Override
    public LibraryAccount getAccount(String studentId) {
        return libraryAccountRepository.findByStudentId(studentId).orElse(null);
    }
}
