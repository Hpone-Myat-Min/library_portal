package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;
import uk.ac.leedsbeckett.hmm.library_portal.repositories.BookRepository;

import java.util.List;

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
    public Book borrowBook(String bookIsbn) {
        Book book = bookRepository.findById(bookIsbn).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book not found with ISBN " + bookIsbn);
        }

        book.setCopies(book.getCopies() - 1);
        return bookRepository.save(book);
    }

    @Override
    public Book returnBook(String bookIsbn) {
        Book book = bookRepository.findById(bookIsbn).orElse(null);
        if (book == null) {
            throw new RuntimeException("Book not found with ISBN " + bookIsbn);
        }

        book.setCopies(book.getCopies() + 1);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
