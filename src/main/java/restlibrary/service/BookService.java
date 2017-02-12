package restlibrary.service;

import restlibrary.model.Book;

public interface BookService {

    Book addNewBook(Book newBook);

    Book getBookById(Long id);
}
