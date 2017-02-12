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
    public void testForAddNewItem() {
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

        Book book = bookService.addNewBook(newBook);
        Book dbBook = bookService.getBookById(book.getId());

        Assert.assertNotNull(dbBook);
        Assert.assertEquals(dbBook.getGenreType(), newBook.getGenreType());
        Assert.assertEquals(dbBook.getTitle(), newBook.getTitle());
    }


    @Test
    public void testForAddNewItemWithoutAnyRequiredAttribute() {
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

        Book book = bookService.addNewBook(newBook);
    }
}
