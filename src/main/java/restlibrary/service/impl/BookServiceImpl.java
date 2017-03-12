package restlibrary.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.exception.service.BookException;
import restlibrary.model.Book;
import restlibrary.repository.BookRepository;
import restlibrary.service.BookService;

import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@Service("bookService")
public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    public void addNewBook(Book newBook) throws BookException {
        validateNewBook(newBook);
        checkIfThatBookExists(newBook);
        bookRepository.addNewBook(newBook);
    }

    private void throwException(String message) throws BookException {
        logger.error(message);
        throw new BookException(message);
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public void removeBook(Book book) {
        bookRepository.removeBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    private void validateNewBook(Book newBook) throws BookException {
        logger.info("Start validate new book with isbn: " + newBook.getIsbn());
        if (StringUtils.isBlank(newBook.getTitle())) {
            throwException("Book title cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newBook.getAuthor_1())) {
            throwException("Book author cannot be empty or blank.");
        }

        if (newBook.getGenreType() == null) {
            throwException("Book genre type cannot be empty or blank.");
        }

        if (newBook.getBookType() == null) {
            throwException("Book type cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newBook.getLanguage())) {
            throwException("Book language cannot be empty or blank.");
        }

        if (newBook.getPages() <= 0) {
            throwException("Book pages have to be above than 0.");
        }

        if (newBook.getReleaseYear() <= 0) {
            throwException("Book release year has to be above than 0.");
        }

        if (StringUtils.isBlank(newBook.getPublishingHouse())) {
            throwException("Book publishing house cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newBook.getIsbn())) {
            throwException("Book isbn cannot be empty or blank.");
        }

        if (newBook.getCopies() <= 0) {
            throwException("Book copies have to be above than 0.");
        }
        logger.info("Validate new book with isbn: " + newBook.getIsbn() + " is finished.");
    }

    private void checkIfThatBookExists(Book newBook) throws BookException {
        logger.info("Check if book with isbn: " + newBook.getIsbn() + " already exists.");

        try {
            Book book = bookRepository.findByISBN(newBook.getIsbn());
            if (book != null) {
                logger.error("Cannot add new book with isbn: " + newBook.getIsbn() + ", because that book already exists in database.");
                throwException("Cannot add new book, because that book already exists in database.");
            }
        } catch (NoResultException exception) {
            logger.info("Book with isbn: " + newBook.getIsbn() + " can be add to database.");
        } finally {
        }
        logger.info("Book with isbn: " + newBook.getIsbn() + " does not exist in database.");
    }
}
