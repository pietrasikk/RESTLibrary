package restlibrary.repository;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface RentalRecordHistoryRepository {

    List<RentalRecord> getReservedBooksList();

    List<RentalRecord> getRentedBooksList();

    List<RentalRecord> getRentedClientBooksList(Long userId);
}
