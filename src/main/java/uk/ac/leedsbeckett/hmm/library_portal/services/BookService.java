package uk.ac.leedsbeckett.hmm.library_portal.services;

import uk.ac.leedsbeckett.hmm.library_portal.entities.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);
//    Boolean borrowBook(String bookIsbn);
//    Boolean returnBook(String bookIsbn);
    List<Book> getBooks();

}
