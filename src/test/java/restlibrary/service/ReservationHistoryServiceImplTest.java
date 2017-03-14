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
import restlibrary.exception.service.ReservationHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.model.RentalRecord;
import restlibrary.model.enums.RentalRecordStatusEnum;

import java.util.List;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class ReservationHistoryServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private ReservationHistoryService reservationHistoryService;

    @Test
    @Sql({"/getReservedBooksList.sql"})
    public void testForGetAllBooksWithReservedStatus() {
        List<RentalRecord> reservedBooksList = reservationHistoryService.getReservedBooksList();

        Assert.assertEquals(reservedBooksList.size(), 4);
        Assert.assertEquals(reservedBooksList.get(0).getRentalRecordStatus(), RentalRecordStatusEnum.RESERVED);
        Assert.assertEquals(reservedBooksList.get(1).getRentalRecordStatus(), RentalRecordStatusEnum.RESERVED);
        Assert.assertEquals(reservedBooksList.get(2).getRentalRecordStatus(), RentalRecordStatusEnum.RESERVED);
        Assert.assertEquals(reservedBooksList.get(3).getRentalRecordStatus(), RentalRecordStatusEnum.RESERVED);
    }

    @Test
    @Sql({"/getRentedBooksList.sql"})
    public void testForGetAllBooksWithRentedStatus() {
        List<RentalRecord> rentedBooksList = reservationHistoryService.getRentedBooksList();

        Assert.assertEquals(rentedBooksList.size(), 4);
        Assert.assertEquals(rentedBooksList.get(0).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
        Assert.assertEquals(rentedBooksList.get(1).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
        Assert.assertEquals(rentedBooksList.get(2).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
        Assert.assertEquals(rentedBooksList.get(3).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
    }

    @Test
    public void testForGetZeroBooksWithReservedStatus() {
        List<RentalRecord> reservedBooksList = reservationHistoryService.getReservedBooksList();
        Assert.assertEquals(reservedBooksList.size(), 0);
    }

    @Test
    public void testForGetZeroBooksWithRentedStatus() {
        List<RentalRecord> rentedBooksList = reservationHistoryService.getRentedBooksList();
        Assert.assertEquals(rentedBooksList.size(), 0);
    }

    @Test
    @Sql({"/getRentedClientBooksList.sql"})
    public void testForGetAllClientBooksWithRentedStatus() throws ReservationHistoryException, UserException {
        Long userId = 1L;
        List<RentalRecord> rentedClientBooksList = reservationHistoryService.getRentedClientBooksList(userId);

        Assert.assertEquals(rentedClientBooksList.size(), 3);
        Assert.assertEquals(rentedClientBooksList.get(0).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
        Assert.assertEquals(rentedClientBooksList.get(1).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
        Assert.assertEquals(rentedClientBooksList.get(2).getRentalRecordStatus(), RentalRecordStatusEnum.RENTED);
    }

    @Test
    @Sql({"/getZeoRentedClientBooksList.sql"})
    public void testForGetZeroClientBooksWithRentedStatus() throws ReservationHistoryException, UserException {
        Long userId = 1L;
        List<RentalRecord> rentedClientBooksList = reservationHistoryService.getRentedClientBooksList(userId);
        Assert.assertEquals(rentedClientBooksList.size(), 0);
    }

    @Test
    public void testForNullClientId() throws ReservationHistoryException, UserException {
        thrown.expect(ReservationHistoryException.class);
        thrown.expectMessage("Client id param cannot be null or empty.");

        Long userId = null;
        List<RentalRecord> rentedClientBooksList = reservationHistoryService.getRentedClientBooksList(userId);
        Assert.assertEquals(rentedClientBooksList.size(), 0);
    }

    @Test
    public void testForWrongClientId() throws ReservationHistoryException, UserException {
        thrown.expect(UserException.class);
        thrown.expectMessage("User with that id: 2 does not exist.");

        Long userId = 2L;
        List<RentalRecord> rentedClientBooksList = reservationHistoryService.getRentedClientBooksList(userId);
        Assert.assertEquals(rentedClientBooksList.size(), 0);
    }
}
