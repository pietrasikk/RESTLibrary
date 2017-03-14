package restlibrary.service;


import restlibrary.model.RentalRecord;

import java.util.List;

public interface ReservationHistoryService {

    List<RentalRecord> getReservedBooksList();

    List<RentalRecord> getRentedBooksList();
}
