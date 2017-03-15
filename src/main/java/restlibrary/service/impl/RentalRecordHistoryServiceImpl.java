package restlibrary.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.exception.service.RentalRecordHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.model.RentalRecord;
import restlibrary.model.User;
import restlibrary.repository.RentalRecordHistoryRepository;
import restlibrary.repository.UserRepository;
import restlibrary.service.RentalRecordHistoryService;

import java.util.List;

@Transactional
@Service("reservationHistory")
public class RentalRecordHistoryServiceImpl implements RentalRecordHistoryService {

    private static final Logger logger = LogManager.getLogger(RentalRecordHistoryServiceImpl.class);

    @Autowired
    private RentalRecordHistoryRepository rentalRecordHistoryRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<RentalRecord> getRentedClientBooksList(Long userId) throws RentalRecordHistoryException, UserException {
        if (userId == null) {
            throw new RentalRecordHistoryException("Client id param cannot be null or empty.");
        }

        User user = userRepository.getUserById(userId);
        if (user == null) {
            logger.error("User with that id: " + userId + " does not exist.");
            throw new UserException("User with that id: " + userId + " does not exist.");
        }
        logger.info("User with that id: " + userId + " exists.");
        logger.info("Start getting list of reserved books by client: " + userId);
        List<RentalRecord> rentedClientBooksList = rentalRecordHistoryRepository.getRentedClientBooksList(userId);
        logger.info("Finish getting list of reserved books by client: " + userId);
        return rentedClientBooksList;
    }
}
