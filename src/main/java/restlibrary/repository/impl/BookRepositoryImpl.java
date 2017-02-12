package restlibrary.repository.impl;

import org.springframework.stereotype.Repository;
import restlibrary.model.Book;
import restlibrary.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Book addNewBook(Book newBook) {
        return entityManager.merge(newBook);
    }

    public Book getBookById(Long id) {
        return entityManager.find(Book.class, id);
    }
}
