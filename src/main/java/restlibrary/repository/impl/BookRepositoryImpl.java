package restlibrary.repository.impl;

import org.springframework.stereotype.Repository;
import restlibrary.model.Book;
import restlibrary.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Book addNewBook(Book newBook) {
        return saveOrUpdate(newBook);
    }

    public Book getBookById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void removeBook(Book book) {
        Book removedBook = entityManager.find(Book.class, book.getId());
        if (removedBook != null) {
            entityManager.remove(removedBook);
        }
    }

    public List<Book> getAllBooks() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = builder.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    public Book updateBook(Book book) {
        return saveOrUpdate(book);
    }

    private Book saveOrUpdate(Book book) {
        return entityManager.merge(book);
    }
}
