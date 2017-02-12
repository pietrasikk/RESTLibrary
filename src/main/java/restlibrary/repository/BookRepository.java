package restlibrary.repository;

import restlibrary.model.Book;

public interface BookRepository {

    Book addNewBook(Book newBook);

    Book getBookById(Long id);
}
