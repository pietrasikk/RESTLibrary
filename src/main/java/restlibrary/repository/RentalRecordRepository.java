package restlibrary.repository;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface RentalRecordRepository {

    RentalRecord addRentalRecord(RentalRecord rentalRecord);

    List<RentalRecord> getReservedOrRentedBooksByBookId(List<Long> books);

    List<RentalRecord> getReserved(Long userId);

    List<RentalRecord> getRented(Long userId);

    void update(RentalRecord rentalRecord);
}
