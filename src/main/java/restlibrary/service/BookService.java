package restlibrary.service;

import restlibrary.exception.service.BookException;
import restlibrary.model.Book;
import restlibrary.model.RemoveBook;
import restlibrary.model.SearchedBook;

import java.util.List;

public interface BookService {

    void addNewBook(Book newBook) throws BookException;

    Book getBookById(Long id);

    void removeBook(RemoveBook book) throws BookException;

    List<Book> getAllBooks();

    List<Book> findBooks(SearchedBook searchedBook) throws BookException;
}
