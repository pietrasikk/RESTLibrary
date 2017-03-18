package restlibrary.repository.impl;


import org.springframework.stereotype.Repository;
import restlibrary.model.RentalRecord;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.RentalRecordHistoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("rentalRecordHistoryRepository")
public class RentalRecordHistoryRepositoryImpl implements RentalRecordHistoryRepository {

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

    @Override
    public List<RentalRecord> getRentedClientBooksList(Long userId) {
        return entityManager.createQuery("from RentalRecord r where r.user.id = :userId and r.rentalRecordStatus = :rentalRecordStatus")
                .setParameter("userId", userId)
                .setParameter("rentalRecordStatus", RentalRecordStatusEnum.RENTED)
                .getResultList();
    }

    @Override
    public List<RentalRecord> getReservedClientBooksList(Long userId) {
        return entityManager.createQuery("from RentalRecord r where r.user.id = :userId and r.rentalRecordStatus = :rentalRecordStatus")
                .setParameter("userId", userId)
                .setParameter("rentalRecordStatus", RentalRecordStatusEnum.RESERVED)
                .getResultList();
    }
}
