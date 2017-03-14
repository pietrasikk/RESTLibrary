package restlibrary.service;


import restlibrary.exception.service.ReservationHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.model.RentalRecord;

import java.util.List;

public interface ReservationHistoryService {

    List<RentalRecord> getReservedBooksList();

    List<RentalRecord> getRentedBooksList();

    List<RentalRecord> getRentedClientBooksList(Long userId) throws ReservationHistoryException, UserException;
}
