package restlibrary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.model.Book;
import restlibrary.repository.BookRepository;
import restlibrary.service.BookService;

@Transactional
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addNewBook(Book newBook) {
        return bookRepository.addNewBook(newBook);
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }
}
