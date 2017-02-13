package restlibrary.services;


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
import restlibrary.model.Book;
import restlibrary.model.enums.GenreTypeEnum;
import restlibrary.model.enums.BookTypeEnum;
import restlibrary.service.BookService;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
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
    public void testForAddNewBook() {
        Book newBook = new Book();
        newBook.setTitle("Test title");
        newBook.setAuthor_1("Test Author 1");
        newBook.setBookType(BookTypeEnum.BOOK);
        newBook.setLanguage("EN");
        newBook.setPages(250);
        newBook.setReleaseYear(LocalDateTime.now().getYear());
        newBook.setPublishingHouse("PUBLISHING_HOUSE");
        newBook.setIsbn("ISBN");
        newBook.setCopies(10);
        newBook.setGenreType(GenreTypeEnum.GUIDEBOOK);

        Assert.assertEquals(bookService.getAllBooks().size(), 0);
        bookService.addNewBook(newBook);
        Assert.assertEquals(bookService.getAllBooks().size(), 1);
    }


    @Test
    public void testForAddNewBookWithoutAnyRequiredAttribute() {
        thrown.expect(PersistenceException.class);

        Book newBook = new Book();
        newBook.setAuthor_1("Test Author 1");
        newBook.setBookType(BookTypeEnum.BOOK);
        newBook.setLanguage("EN");
        newBook.setPages(250);
        newBook.setReleaseYear(LocalDateTime.now().getYear());
        newBook.setPublishingHouse("PUBLISHING_HOUSE");
        newBook.setIsbn("ISBN");
        newBook.setCopies(10);
        newBook.setGenreType(GenreTypeEnum.GUIDEBOOK);

        bookService.addNewBook(newBook);
    }


    @Test
    public void testForRemoveBook() {
        Book newBook_1 = new Book();
        newBook_1.setTitle("Test title");
        newBook_1.setAuthor_1("Test Author 1");
        newBook_1.setBookType(BookTypeEnum.BOOK);
        newBook_1.setLanguage("EN");
        newBook_1.setPages(250);
        newBook_1.setReleaseYear(LocalDateTime.now().getYear());
        newBook_1.setPublishingHouse("PUBLISHING_HOUSE");
        newBook_1.setIsbn("ISBN");
        newBook_1.setCopies(10);
        newBook_1.setGenreType(GenreTypeEnum.GUIDEBOOK);

        Book newBook_2 = new Book();
        newBook_2.setTitle("Test title");
        newBook_2.setAuthor_1("Test Author 2");
        newBook_2.setBookType(BookTypeEnum.BOOK);
        newBook_2.setLanguage("DE");
        newBook_2.setPages(200);
        newBook_2.setReleaseYear(LocalDateTime.now().getYear());
        newBook_2.setPublishingHouse("PUBLISHING_HOUSE");
        newBook_2.setIsbn("ISBN");
        newBook_2.setCopies(5);
        newBook_2.setGenreType(GenreTypeEnum.DIARY);

        Assert.assertEquals(bookService.getAllBooks().size(), 0);
        bookService.addNewBook(newBook_1);
        Book book = bookService.addNewBook(newBook_2);
        Assert.assertEquals(bookService.getAllBooks().size(), 2);
        bookService.removeBook(book);
        Assert.assertEquals(bookService.getAllBooks().size(), 1);
    }
}
