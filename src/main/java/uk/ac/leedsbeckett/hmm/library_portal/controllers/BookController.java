package uk.ac.leedsbeckett.hmm.library_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;
import uk.ac.leedsbeckett.hmm.library_portal.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        // Constructor Injection
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.getBooks();
        return new ResponseEntity<>( bookList,HttpStatus.OK);
    }

}
