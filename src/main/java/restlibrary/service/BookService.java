package restlibrary.service;

import restlibrary.model.Book;

import java.util.List;

public interface BookService {

    Book addNewBook(Book newBook);

    Book getBookById(Long id);

    void removeBook(Book book);

    List<Book> getAllBooks();
}
