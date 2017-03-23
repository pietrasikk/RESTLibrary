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

    @Override
    public List<RentalRecord> getReserved(Long userId) {
        return entityManager.createQuery("from RentalRecord r where r.user.id = :userId and r.rentalRecordStatus = :rentalRecordStatus")
                .setParameter("userId", userId)
                .setParameter("rentalRecordStatus", RentalRecordStatusEnum.RESERVED)
                .getResultList();
    }

    @Override
    public List<RentalRecord> getRented(Long userId) {
        return entityManager.createQuery("from RentalRecord r where r.user.id = :userId and r.rentalRecordStatus = :rentalRecordStatus")
                .setParameter("userId", userId)
                .setParameter("rentalRecordStatus", RentalRecordStatusEnum.RENTED)
                .getResultList();
    }

    @Override
    public void update(RentalRecord rentalRecord) {
        entityManager.merge(rentalRecord);
    }

    @Override
    public void removeUserRentalRecord(Long userId) {
        entityManager.createQuery("delete from RentalRecord r where r.user.id = :userId")
        .setParameter("userId", userId).executeUpdate();
    }
}
