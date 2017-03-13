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
import restlibrary.model.RentalRecord;
import restlibrary.model.enums.RentalRecordStatusEnum;

import java.util.List;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class ReservationHistoryServiceImplTest {

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
    public void testForGetZeroBooksWithReservedStatus() {
        List<RentalRecord> reservedBooksList = reservationHistoryService.getReservedBooksList();

        Assert.assertEquals(reservedBooksList.size(), 0);
    }
}
