package restlibrary.repository;

import restlibrary.model.Book;

import java.util.List;

public interface BookRepository {

    Book addNewBook(Book newBook);

    Book getBookById(Long id);

    void removeBook(Book book);

    List<Book> getAllBooks();
}
