package restlibrary.service;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.configuration.HibernateConfigurationTest;
import restlibrary.exception.service.BookException;
import restlibrary.model.Book;
import restlibrary.model.RemoveBook;
import restlibrary.model.SearchedBook;
import restlibrary.model.enums.BookTypeEnum;
import restlibrary.model.enums.GenreTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class BookServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private BookService bookService;

    @Test
    public void testForAddNewBook() throws BookException {
        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        Assert.assertEquals(bookService.getAllBooks().size(), 0);
        bookService.addNewBook(book);
        Assert.assertEquals(bookService.getAllBooks().size(), 1);
    }

    @Test
    public void testForAddTheSameBook() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Cannot add new book, because that book already exists in database.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
        bookService.addNewBook(book);

    }

    @Test
    public void testForAddNewBookWithoutTitle() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book title cannot be empty or blank.");

        Book book = new Book();
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutAuthor() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book author cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutCopies() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book copies have to be above than 0.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutBookType() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book type cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutGenreType() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book genre type cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutISBN() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book isbn cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutLanguage() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book language cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutPages() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book pages have to be above than 0.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPublishingHouse("Test_publishing_house");
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutPublishingHouse() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book publishing house cannot be empty or blank.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setReleaseYear(1950);

        bookService.addNewBook(book);
    }

    @Test
    public void testForAddNewBookWithoutReleaseYer() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Book release year has to be above than 0.");

        Book book = new Book();
        book.setTitle("Test_title");
        book.setAuthor_1("Test_author_1");
        book.setCopies(5);
        book.setBookType(BookTypeEnum.BOOK);
        book.setGenreType(GenreTypeEnum.BIOGRAPHY);
        book.setIsbn("Test_ISBN");
        book.setLanguage("Test_language");
        book.setPages(20);
        book.setPublishingHouse("Test_publishing_house");

        bookService.addNewBook(book);
    }

    @Test
    @Sql({"/searchBooks.sql"})
    public void testForSearchAllTypeOfCriteria() throws BookException {
        SearchedBook searchedBook = new SearchedBook();

        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 0);

        searchedBook.setTitle("Test_title_1");
        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 1);

        searchedBook.setAuthor("Test_author_2");
        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 2);

        searchedBook.setPublishingHouse("P_H_3");
        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 3);

        searchedBook.setIsbn("ISBN_4");
        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 4);
    }

    @Test
    public void testForSearchBookWhichDoesNotExist() throws BookException {
        SearchedBook searchedBook = new SearchedBook();
        searchedBook.setTitle("Test_title_1");

        Assert.assertEquals(bookService.findBooks(searchedBook).size(), 0);
    }

    @Test
    public void testForSearchBookWithNullCriteria() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Criteria for book cannot be null or empty.");

        bookService.findBooks(null);
    }

    @Test
    @Sql({"/testForRemoveBook.sql"})
    public void testForRemoveBook() throws BookException {
        RemoveBook removeBook = new RemoveBook();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        removeBook.setBooksId(ids);
        List<Book> allBooksBefore = bookService.getAllBooks();
        Assert.assertEquals(allBooksBefore.size(), 4);
        bookService.removeBook(removeBook);
        List<Book> allBooksAfter = bookService.getAllBooks();
        Assert.assertEquals(allBooksAfter.size(), 0);
    }

    @Test
    @Sql({"/testForRemoveBook.sql"})
    public void testForRemoveNonExistingBook() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("One of the books is not in database.");

        RemoveBook removeBook = new RemoveBook();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(5L);
        removeBook.setBooksId(ids);
        bookService.removeBook(removeBook);
    }

    public void testForRemoveBooksWithNoInput() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("There are no books to remove.");

        RemoveBook removeBook = new RemoveBook();
        List<Long> ids = new ArrayList<>();
        removeBook.setBooksId(ids);
        bookService.removeBook(removeBook);
    }
}
