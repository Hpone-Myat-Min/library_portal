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

    @PutMapping("/borrow/{bookIsbn}")
    public ResponseEntity<Book> borrowBook(@PathVariable String bookIsbn) {
        Book borrowingBook = bookService.borrowBook(bookIsbn);
        return new ResponseEntity<>( borrowingBook,HttpStatus.OK);
    }

    @PutMapping("/return/{bookIsbn}")
    public ResponseEntity<Book> returnBook(@PathVariable String bookIsbn) {
        Book returningBook = bookService.returnBook(bookIsbn);
        return new ResponseEntity<>( returningBook,HttpStatus.OK);
    }

}
