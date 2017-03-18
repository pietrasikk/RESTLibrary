package restlibrary.repository;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface RentalRecordRepository {

    RentalRecord addRentalRecord(RentalRecord rentalRecord);

    List<RentalRecord> getReservedOrRentedBooksByBookId(List<Long> books);
}
