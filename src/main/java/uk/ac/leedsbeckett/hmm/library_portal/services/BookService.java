package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);
    Book borrowBook(String bookIsbn);
    Book returnBook(String bookIsbn);
    List<Book> getBooks();

}
