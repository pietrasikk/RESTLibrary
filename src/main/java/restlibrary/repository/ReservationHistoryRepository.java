package restlibrary.repository;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface ReservationHistoryRepository {

    List<RentalRecord> getReservedBooksList();

    List<RentalRecord> getRentedBooksList();

    List<RentalRecord> getRentedClientBooksList(Long userId);
}
