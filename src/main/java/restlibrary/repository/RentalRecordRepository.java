package restlibrary.repository;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface RentalRecordRepository {

    List<RentalRecord> getUserHistory(long userId); //user history

    List<RentalRecord> getBookHistory(long bookId); //specific book history

    List<RentalRecord> getRentedBooksList(); //current rented books

    List<RentalRecord> getReservedBooksList(); //current reserved books

    RentalRecord addRentalRecord(RentalRecord rentalRecord);
}
