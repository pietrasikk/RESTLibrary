package restlibrary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restlibrary.exception.service.BookException;
import restlibrary.exception.service.RentalRecordException;
import restlibrary.exception.service.RentalRecordHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.message.SuccessMessageResponse;
import restlibrary.model.*;
import restlibrary.service.BookService;
import restlibrary.service.RentalRecordHistoryService;
import restlibrary.service.RentalRecordService;
import restlibrary.service.UserService;

import java.util.List;

@RestController
public class LibraryController {

    private static final Logger logger = LogManager.getLogger(LibraryController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private RentalRecordService rentalRecordService;

    @Autowired
    private RentalRecordHistoryService rentalRecordHistoryService;

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SuccessMessageResponse> addNewUser(@RequestBody User newUser) throws UserException {
        logger.info("Start create new user with login: " + newUser.getLogin());
        userService.addNewUser(newUser);
        SuccessMessageResponse successMessageResponse = new SuccessMessageResponse(HttpStatus.OK.value(), "New user was created.");
        logger.info("New user with login: " + newUser.getLogin() + " has been created.");
        return new ResponseEntity<SuccessMessageResponse>(successMessageResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/addNewBook", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SuccessMessageResponse> addNewBook(@RequestBody Book newBook) throws BookException {
        logger.info("Start add new book with isbn: " + newBook.getIsbn());
        bookService.addNewBook(newBook);
        SuccessMessageResponse successMessageResponse = new SuccessMessageResponse(HttpStatus.OK.value(), "New book has beed added.");
        logger.info("New book with isbn: " + newBook.getIsbn() + " has been added.");
        return new ResponseEntity<SuccessMessageResponse>(successMessageResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllReservedBooks", method = RequestMethod.GET, produces = "application/json")
    public List<RentalRecord> getAllReservedBooks() {
        return rentalRecordHistoryService.getReservedBooksList();
    }

    @RequestMapping(value = "/getAllRentedBooks", method = RequestMethod.GET, produces = "application/json")
    public List<RentalRecord> getAllRentedBooks() {
        return rentalRecordHistoryService.getRentedBooksList();
    }

    @RequestMapping(value = "/getAllClientRentedBooks/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<RentalRecord> getAllClientRentedBooks(@PathVariable("userId") Long userId) throws RentalRecordHistoryException, UserException {
        return rentalRecordHistoryService.getRentedClientBooksList(userId);
    }

    @RequestMapping(value = "/findBooks", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Book> findBooks(@RequestBody SearchedBook searchedBook) throws BookException {
        return bookService.findBooks(searchedBook);
    }

    @RequestMapping(value = "/reserveBooks", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SuccessMessageResponse> reserveBooks(@RequestBody RentedBook rentedBooks) throws RentalRecordException {
        rentalRecordService.reserveBooks(rentedBooks);
        SuccessMessageResponse successMessageResponse = new SuccessMessageResponse(HttpStatus.OK.value(), "Books have been reserved.");
        return new ResponseEntity<SuccessMessageResponse>(successMessageResponse, HttpStatus.OK);
    }
}

