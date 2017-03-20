package restlibrary.service;

import restlibrary.exception.service.RentalRecordException;
import restlibrary.model.RentedBook;


public interface RentalRecordService {

    void reserveBooks(RentedBook rentedBooks) throws RentalRecordException;

    void rentBooks(Long userId) throws RentalRecordException;
}
