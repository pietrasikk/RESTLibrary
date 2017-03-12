package restlibrary.service;

import restlibrary.exception.service.BookException;
import restlibrary.model.Book;

import java.util.List;

public interface BookService {

    void addNewBook(Book newBook) throws BookException;

    Book getBookById(Long id);

    void removeBook(Book book);

    List<Book> getAllBooks();
}
