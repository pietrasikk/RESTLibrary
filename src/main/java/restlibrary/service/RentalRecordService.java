package restlibrary.service;

import restlibrary.exception.service.RentalRecordException;
import restlibrary.model.RentedBook;

import java.util.List;

public interface RentalRecordService {

    String rentBooks(Long userId, List<Long> booksIds);

    void reserveBooks(RentedBook rentedBooks) throws RentalRecordException;
}
