package restlibrary.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.model.RentalRecord;
import restlibrary.repository.ReservationHistoryRepository;
import restlibrary.service.ReservationHistoryService;

import java.util.List;

@Transactional
@Service("reservationHistory")
public class ReservationHistoryServiceImpl implements ReservationHistoryService {

    private static final Logger logger = LogManager.getLogger(ReservationHistoryServiceImpl.class);

    @Autowired
    private ReservationHistoryRepository rentalRecordHistoryRepository;

    @Override
    public List<RentalRecord> getReservedBooksList() {
        logger.info("Start getting list of reserved books.");
        List<RentalRecord> reservedBooksList = rentalRecordHistoryRepository.getReservedBooksList();
        logger.info("Finish getting list of reserved books.");
        return reservedBooksList;
    }

    @Override
    public List<RentalRecord> getRentedBooksList() {
        logger.info("Start getting list of rented books.");
        List<RentalRecord> rentedBooksList = rentalRecordHistoryRepository.getRentedBooksList();
        logger.info("Finish getting list of rented books.");
        return rentedBooksList;
    }
}
