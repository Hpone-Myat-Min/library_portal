package uk.ac.leedsbeckett.hmm.library_portal.services;

import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;
import uk.ac.leedsbeckett.hmm.library_portal.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl( BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

//    @Override
//    public Boolean borrowBook(String bookIsbn) {
//
//        Book book = bookRepository.findById(bookIsbn).orElse(null);
//        if (book == null) {
//            throw new RuntimeException("Book not found with ISBN " + bookIsbn);
//        }
//
//        if(book.getCopies() == 0){
//            return false;
//        }
//
//        book.setCopies(book.getCopies() - 1);
//        bookRepository.save(book);
//        return true;
//    }

//    @Override
//    public Boolean returnBook(String bookIsbn) {
//        Book book = bookRepository.findById(bookIsbn).orElse(null);
//        if (book == null) {
//            throw new RuntimeException("Book not found with ISBN " + bookIsbn);
//        }
//
//        book.setCopies(book.getCopies() + 1);
//        bookRepository.save(book);
//        return true;
//    }
//

}
