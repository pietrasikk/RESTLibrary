package restlibrary.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.model.Book;
import restlibrary.model.RentalRecord;
import restlibrary.model.User;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.BookRepository;
import restlibrary.repository.RentalRecordRepository;
import restlibrary.repository.UserRepository;
import restlibrary.service.RentalRecordService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("rentalRecordService")
public class RentalRecordServiceImpl implements RentalRecordService {

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String rentBooks(Long userId, List<Long> booksIds) {

        if (booksIds.isEmpty()) {
            return "There is no books to rent.";
        }

        User user = userRepository.getUserById(userId);
        List<RentalRecord> rentalRecords = new ArrayList<>();

        for (Long id : booksIds) {
            Book book = bookRepository.getBookById(id);

            RentalRecord rentalRecord = new RentalRecord();
            rentalRecord.setUser(user);
            rentalRecord.setBook(book);
            rentalRecord.setRentalDate(LocalDateTime.now());
            rentalRecord.setRentalRecordStatus(RentalRecordStatusEnum.RENTED);
            rentalRecords.add(rentalRecordRepository.addRentalRecord(rentalRecord));

            book.setCopies(book.getCopies() - 1);
            bookRepository.updateBook(book);

        }
        user.setRentalRecords(rentalRecords);
        userRepository.updateUser(user);

        return "The books are rented.";
    }
}
