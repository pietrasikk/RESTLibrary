package restlibrary.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.exception.service.BookException;
import restlibrary.model.Book;
import restlibrary.model.RemoveBook;
import restlibrary.model.SearchedBook;
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

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public void removeBook(RemoveBook books) throws BookException {
        logger.info("Start removing books...");
        if (books == null  || books.getBooksId() == null|| books.getBooksId().isEmpty()) {
            logger.error("There are no books to remove.");
            throwException("There are no books to remove.");
        }
        List<Book> booksByIds = bookRepository.getBooksByIds(books.getBooksId());
        if (booksByIds.size() != books.getBooksId().size()) {
            logger.error("One of the books is not in database: " + books.toString());
            throwException("One of the books is not in database.");
        }
        bookRepository.removeBook(books.getBooksId());
        logger.info("Stop removing books...");
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> findBooks(SearchedBook searchedBook) throws BookException {
        if (searchedBook == null) {
            logger.error("Criteria for book cannot be null or empty.");
            throw new BookException("Criteria for book cannot be null or empty.");
        }
        logger.info("Start searching for criteria: " + searchedBook.toString());
        List<Book> books = bookRepository.findBooks(searchedBook);
        logger.info("Stop searching for criteria: " + searchedBook.toString() + ". Found: " + books.size() + " books.");
        return books;
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

    private void throwException(String message) throws BookException {
        logger.error(message);
        throw new BookException(message);
    }
}
