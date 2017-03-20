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
import restlibrary.exception.service.RentalRecordException;
import restlibrary.model.Book;
import restlibrary.model.RentalRecord;
import restlibrary.model.RentedBook;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.RentalRecordHistoryRepository;

import java.util.ArrayList;
import java.util.List;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class RentalRecordServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RentalRecordService rentalRecordService;

    @Autowired
    private RentalRecordHistoryRepository rentalRecordHistoryRepository;

    @Autowired
    private BookService bookService;

    @Test
    @Sql({"/testForReserveBooks.sql"})
    public void testForReserveBooks() throws RentalRecordException {
        RentedBook rentedBook = new RentedBook();
        rentedBook.setUserId(1L);
        ArrayList<Long> booksId = new ArrayList<>();
        booksId.add(1L);
        booksId.add(3L);
        rentedBook.setBooksId(booksId);

        Book book_1_Before = bookService.getBookById(1L);
        Book book_3_Before = bookService.getBookById(3L);
        List<RentalRecord> rentedBooksBefore = rentalRecordHistoryRepository.getReservedClientBooksList(1L);
        Assert.assertEquals(rentedBooksBefore.size(), 0);
        Assert.assertEquals(book_1_Before.getCopies(), 12);
        Assert.assertEquals(book_3_Before.getCopies(), 5);

        rentalRecordService.reserveBooks(rentedBook);

        Book book_1_After = bookService.getBookById(1L);
        Book book_3_After = bookService.getBookById(3L);
        List<RentalRecord> rentedBooksAfter = rentalRecordHistoryRepository.getReservedClientBooksList(1L);
        Assert.assertEquals(rentedBooksAfter.size(), 2);
        Assert.assertEquals(rentedBooksAfter.get(0).getRentalRecordStatus(), RentalRecordStatusEnum.RESERVED);
        Assert.assertEquals(book_1_After.getCopies(), 11);
        Assert.assertEquals(book_3_After.getCopies(), 4);
    }

    @Test
    @Sql({"/testForReserveTheSameBooks.sql"})
    public void testForReserveTheSameBooks() throws RentalRecordException {
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("You already have reserved or rented one of this books.");

        RentedBook rentedBook = new RentedBook();
        rentedBook.setUserId(1L);
        ArrayList<Long> booksId = new ArrayList<>();
        booksId.add(1L);
        rentedBook.setBooksId(booksId);

        rentalRecordService.reserveBooks(rentedBook);
    }

    @Test
    @Sql({"/testForReserveBookWithNoCopiesLeft.sql"})
    public void testForReserveBookWithNoCopiesLeft() throws RentalRecordException {
        long bookId = 1L;
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("There is no available copies for book id: " + bookId);

        RentedBook rentedBook = new RentedBook();
        rentedBook.setUserId(1L);
        ArrayList<Long> booksId = new ArrayList<>();
        booksId.add(1L);
        rentedBook.setBooksId(booksId);

        rentalRecordService.reserveBooks(rentedBook);
    }

    @Test
    public void testForReservBooksWithEmptyUserId() throws RentalRecordException {
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("User id cannot be null or empty.");

        RentedBook rentedBook = new RentedBook();
        ArrayList<Long> booksId = new ArrayList<>();
        booksId.add(1L);
        rentedBook.setBooksId(booksId);

        rentalRecordService.reserveBooks(rentedBook);
    }

    @Test
    public void testForReservBooksWithEmptyBooksId() throws RentalRecordException {
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("Books cannot be null or empty.");

        RentedBook rentedBook = new RentedBook();
        rentedBook.setUserId(1L);

        rentalRecordService.reserveBooks(rentedBook);
    }

    @Test
    @Sql({"/testForRentBooks.sql"})
    public void testForRentBooks() throws RentalRecordException {
        long userId = 1L;
        Assert.assertEquals(rentalRecordHistoryRepository.getRentedClientBooksList(userId).size(), 1);
        Assert.assertEquals(rentalRecordHistoryRepository.getReservedClientBooksList(userId).size(), 1);

        rentalRecordService.rentBooks(userId);

        Assert.assertEquals(rentalRecordHistoryRepository.getRentedClientBooksList(userId).size(), 2);
        Assert.assertEquals(rentalRecordHistoryRepository.getReservedClientBooksList(userId).size(), 0);
    }

    @Test
    public void testForRentBooksWithWrongUser() throws RentalRecordException {
        long userId = 2L;
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("There is no user with id: " + userId);

        rentalRecordService.rentBooks(userId);
    }

    @Test
    @Sql({"/testForRentBooksWithoutReservedBooks.sql"})
    public void testForRentBooksWithoutReservedBooks() throws RentalRecordException {
        long userId = 1L;
        thrown.expect(RentalRecordException.class);
        thrown.expectMessage("This user: " + userId + " does not have any reserved books.");

        rentalRecordService.rentBooks(userId);
    }
}
