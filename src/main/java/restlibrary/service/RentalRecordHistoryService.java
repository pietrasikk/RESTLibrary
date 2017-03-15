package restlibrary.service;


import restlibrary.exception.service.RentalRecordHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.model.RentalRecord;

import java.util.List;

public interface RentalRecordHistoryService {

    List<RentalRecord> getReservedBooksList();

    List<RentalRecord> getRentedBooksList();

    List<RentalRecord> getRentedClientBooksList(Long userId) throws RentalRecordHistoryException, UserException;
}
