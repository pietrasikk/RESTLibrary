package restlibrary.service;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.configuration.HibernateConfigurationTest;
import restlibrary.exception.service.BookException;
import restlibrary.model.Book;
import restlibrary.model.enums.BookTypeEnum;
import restlibrary.model.enums.GenreTypeEnum;

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
}
