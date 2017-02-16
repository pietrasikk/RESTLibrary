package restlibrary.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.configuration.HibernateConfigurationTest;
import restlibrary.model.Book;
import restlibrary.model.User;

import java.util.Arrays;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class RentalRecordServiceImplTest {

    @Autowired
    private RentalRecordService rentalRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Test
    @Sql({"/testForRentBooks.sql"})
    public void testForRentBooks() {
        User user = userService.getUserById(1l);
        Book book_1 = bookService.getBookById(1l);
        Book book_2 = bookService.getBookById(2l);
        Book book_3 = bookService.getBookById(3l);

        Assert.assertEquals(user.getRentalRecords().size(), 0);
        Assert.assertEquals(book_1.getCopies(), 12);
        Assert.assertEquals(book_2.getCopies(), 3);
        Assert.assertEquals(book_3.getCopies(), 7);

        rentalRecordService.rentBooks(user.getId(), Arrays.asList(book_1.getId(), book_2.getId(), book_3.getId()));

        Assert.assertNotEquals(user.getRentalRecords(), 0);
        Assert.assertEquals(user.getRentalRecords().size(), 3);
        Assert.assertEquals(book_1.getCopies(), 11);
        Assert.assertEquals(book_2.getCopies(), 2);
        Assert.assertEquals(book_3.getCopies(), 6);
    }
}
