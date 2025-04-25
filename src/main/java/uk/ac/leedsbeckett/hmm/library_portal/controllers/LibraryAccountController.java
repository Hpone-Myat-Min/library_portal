package uk.ac.leedsbeckett.hmm.library_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.library_portal.entities.LibraryAccount;
import uk.ac.leedsbeckett.hmm.library_portal.services.LibraryAccountService;

@RestController
@RequestMapping("/libraryAccount")
public class LibraryAccountController {

    private final LibraryAccountService libraryAccountService;

    public LibraryAccountController(LibraryAccountService libraryAccountService) {
        this.libraryAccountService = libraryAccountService;
    }

    @PostMapping("/create")
    public ResponseEntity<LibraryAccount> createAccount(@RequestBody LibraryAccount account) {
        LibraryAccount newAccount = libraryAccountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<LibraryAccount> updateAccount(@PathVariable String studentId, @RequestBody LibraryAccount account) {
        LibraryAccount updatedAccount = libraryAccountService.updateAccount(studentId, account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<LibraryAccount> getAccount(@PathVariable String studentId) {
        LibraryAccount account = libraryAccountService.getAccount(studentId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
