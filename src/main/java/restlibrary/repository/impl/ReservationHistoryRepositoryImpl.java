package restlibrary.repository.impl;


import org.springframework.stereotype.Repository;
import restlibrary.model.RentalRecord;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.ReservationHistoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("rentalRecordHistoryRepository")
public class ReservationHistoryRepositoryImpl implements ReservationHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RentalRecord> getReservedBooksList() {
        return entityManager.createQuery("from RentalRecord r where r.rentalRecordStatus = :rentalRecordStatus").setParameter("rentalRecordStatus", RentalRecordStatusEnum.RESERVED).getResultList();
    }

    @Override
    public List<RentalRecord> getRentedBooksList() {
        return entityManager.createQuery("from RentalRecord r where r.rentalRecordStatus = :rentalRecordStatus").setParameter("rentalRecordStatus", RentalRecordStatusEnum.RENTED).getResultList();
    }
}
