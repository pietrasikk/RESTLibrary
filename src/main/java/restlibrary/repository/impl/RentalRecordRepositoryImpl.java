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
    public RentalRecord addRentalRecord(RentalRecord rentalRecord) {
        return entityManager.merge(rentalRecord);
    }

    @Override
    public List<RentalRecord> getReservedOrRentedBooksByBookId(List<Long> books) {
        return entityManager.createQuery("from RentalRecord r where r.rentalRecordStatus = :rented or r.rentalRecordStatus = :reserved and r.book.id in (:books)")
                .setParameter("rented", RentalRecordStatusEnum.RENTED)
                .setParameter("reserved", RentalRecordStatusEnum.RESERVED)
                .setParameter("books", books)
                .getResultList();
    }
}
