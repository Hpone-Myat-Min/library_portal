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

}
