package restlibrary.repository.impl;


import org.springframework.stereotype.Repository;
import restlibrary.model.RentalRecord;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.RentalRecordRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("rentalRecordRepository")
public class RentalRecordRepositoryImpl implements RentalRecordRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RentalRecord> getUserHistory(long userId) {
        return entityManager.createQuery("from RentalRecord r "
                + "where r.user.id = :userId AND r.rentalRecordStatus = :rentedStatus ORDER BY rentalDate desc", RentalRecord.class)
                .setParameter("userId", userId)
                .setParameter("rentedStatus", RentalRecordStatusEnum.RENTED)
                .getResultList();
    }

    @Override
    public List<RentalRecord> getBookHistory(long bookId) {
        return null;
    }

    @Override
    public List<RentalRecord> getRentedBooksList() {
//        return entityManager.createQuery("from RentalRecord r "
//                + "where r.customer.id = :customerId ORDER BY rentalDate desc", RentalRecord.class)
//                .setParameter("customerId", customerId)
//                .getResultList();
        return null;
    }

    @Override
    public List<RentalRecord> getReservedBooksList() {
        return null;
    }

    @Override
    public RentalRecord addRentalRecord(RentalRecord rentalRecord) {
        return entityManager.merge(rentalRecord);
    }
}
