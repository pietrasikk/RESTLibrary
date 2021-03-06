package restlibrary.repository;

import restlibrary.model.Book;
import restlibrary.model.SearchedBook;

import java.util.List;

public interface BookRepository {

    void addNewBook(Book newBook);

    Book getBookById(Long id);

    void removeBook(List<Long> bookIds);

    List<Book> getAllBooks();

    Book updateBook(Book book);

    Book findByISBN(String isbn);

    List<Book> findBooks(SearchedBook searchedBook);

    List<Book> getBooksByIds(List<Long> books);
}
