package restlibrary.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.exception.service.RentalRecordException;
import restlibrary.model.Book;
import restlibrary.model.RentalRecord;
import restlibrary.model.RentedBook;
import restlibrary.model.User;
import restlibrary.model.enums.RentalRecordStatusEnum;
import restlibrary.repository.BookRepository;
import restlibrary.repository.RentalRecordRepository;
import restlibrary.repository.UserRepository;
import restlibrary.service.RentalRecordService;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service("rentalRecordService")
public class RentalRecordServiceImpl implements RentalRecordService {

    private static final Logger logger = LogManager.getLogger(RentalRecordServiceImpl.class);

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void reserveBooks(RentedBook rentedBooks) throws RentalRecordException {
        checkUserId(rentedBooks.getUserId());
        checkBooks(rentedBooks.getBooksId());
        logger.info("Start reservation books for client with id: " + rentedBooks.getUserId());
        checkIfUserAlreadyHaveThisBooks(rentedBooks);
        prepareReservation(rentedBooks);
        logger.info("End reservation books for client with id: " + rentedBooks.getUserId());
    }

    @Override
    public void returnBooks(RentedBook rentedBooks) throws RentalRecordException {
        checkIfUserExists(rentedBooks.getUserId());
        logger.info("Start giving back books for client with id: " + rentedBooks.getUserId());
        List<RentalRecord> rented = rentalRecordRepository.getRented(rentedBooks.getUserId());
        checkIfUserRentedThisBooks(rentedBooks, rented);
        returnBooksAndUpdateRentalRecord(rentedBooks, rented);
        logger.info("End giving back books for client with id: " + rentedBooks.getUserId());
    }

    @Override
    public void rentBooks(Long userId) throws RentalRecordException {
        checkIfUserExists(userId);
        logger.info("Start renting books for client with id: " + userId);
        List<RentalRecord> reservedBooks = rentalRecordRepository.getReserved(userId);
        checkIfUserHasReservedBooks(userId, reservedBooks);
        updateBooksStatus(reservedBooks);
        logger.info("End renting books for client with id: " + userId);
    }

    private void returnBooksAndUpdateRentalRecord(RentedBook rentedBooks, List<RentalRecord> rented) {
        logger.info("Return books and update Rental Records");
        List<Book> booksByIds = bookRepository.getBooksByIds(rentedBooks.getBooksId());
        for (Book book : booksByIds) {
            book.setCopies(book.getCopies() + 1);
            bookRepository.updateBook(book);
        }
        for (RentalRecord rentalRecord : rented) {
            rentalRecord.setReturnDate(LocalDateTime.now());
            rentalRecord.setRentalRecordStatus(RentalRecordStatusEnum.RETURNED);
            rentalRecordRepository.update(rentalRecord);
        }
        logger.info("Saved books and Rental Records into Database.");
    }

    private void checkIfUserRentedThisBooks(RentedBook rentedBooks, List<RentalRecord> rented) throws RentalRecordException {
        logger.info("Check if user with id: " + rentedBooks.getUserId() + " rented this books.");
        if (rented.size() != rentedBooks.getBooksId().size()) {
            logger.error("User with id: " + rentedBooks.getUserId() + " did not rent one of this books.");
            throw new RentalRecordException("You did not rent one of this books.");
        }
        logger.info("User with id: " + rentedBooks.getUserId() + " rented this books.");
    }

    private void updateBooksStatus(List<RentalRecord> reservedBooks) {
        for (RentalRecord rentalRecord : reservedBooks) {
            rentalRecord.setRentalRecordStatus(RentalRecordStatusEnum.RENTED);
            rentalRecord.setRentalDate(LocalDateTime.now());
            rentalRecordRepository.update(rentalRecord);
        }
    }

    private void checkIfUserHasReservedBooks(Long userId, List<RentalRecord> reservedBooks) throws RentalRecordException {
        if (reservedBooks == null || reservedBooks.isEmpty()) {
            logger.error("This user: " + userId + " does not have any reserved books.");
            throw new RentalRecordException("This user: " + userId + " does not have any reserved books.");
        }
    }

    private void checkIfUserExists(Long userId) throws RentalRecordException {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            logger.error("There is no user with id: " + userId);
            throw new RentalRecordException("There is no user with id: " + userId);
        }
    }

    private void prepareReservation(RentedBook rentedBooks) throws RentalRecordException {
        logger.info("Update books and prepare Rental Records");
        User user = userRepository.getUserById(rentedBooks.getUserId());
        for (Long id : rentedBooks.getBooksId()) {
            Book book = bookRepository.getBookById(id);
            checkIfBookCopiesIsZero(book);

            RentalRecord rentalRecord = new RentalRecord();
            rentalRecord.setUser(user);
            rentalRecord.setBook(book);
            rentalRecord.setRentalRecordStatus(RentalRecordStatusEnum.RESERVED);
            rentalRecordRepository.addRentalRecord(rentalRecord);

            book.setCopies(book.getCopies() - 1);
            bookRepository.updateBook(book);
        }
        logger.info("Saved books and Rental Records into Database.");
    }

    private void checkIfBookCopiesIsZero(Book book) throws RentalRecordException {
        if (book.getCopies() <= 0) {
            logger.error("There is no available copies for book id: " + book.getId());
            throw new RentalRecordException("There is no available copies for book id: " + book.getId());
        }
    }

    private void checkBooks(List<Long> books) throws RentalRecordException {
        if (books == null || books.isEmpty()) {
            logger.error("Books cannot be null or empty.");
            throw new RentalRecordException("Books cannot be null or empty.");
        }
    }

    private void checkUserId(Long userId) throws RentalRecordException {
        if (userId == null || userId == 0L) {
            logger.error("User id cannot be null or empty.");
            throw new RentalRecordException("User id cannot be null or empty.");
        }
    }

    private void checkIfUserAlreadyHaveThisBooks(RentedBook rentedBooks) throws RentalRecordException {
        logger.info("Check if user with id: " + rentedBooks.getUserId() + " already have one of this books.");
        List<RentalRecord> reservedOrRentedBooksByBookId = rentalRecordRepository.getReservedOrRentedBooksByBookId(rentedBooks.getBooksId());
        if (reservedOrRentedBooksByBookId.size() != 0) {
            logger.error("User with id: " + rentedBooks.getUserId() + " already have this books.");
            throw new RentalRecordException("You already have reserved or rented one of this books.");
        }
        logger.info("User with id: " + rentedBooks.getUserId() + " does not have this books.");
    }
}
