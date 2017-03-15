package restlibrary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restlibrary.exception.service.BookException;
import restlibrary.exception.service.ReservationHistoryException;
import restlibrary.exception.service.UserException;
import restlibrary.message.SuccessMessageResponse;
import restlibrary.model.Book;
import restlibrary.model.RentalRecord;
import restlibrary.model.SearchedBook;
import restlibrary.model.User;
import restlibrary.service.BookService;
import restlibrary.service.ReservationHistoryService;
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
    private ReservationHistoryService reservationHistoryService;

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
        return reservationHistoryService.getReservedBooksList();
    }

    @RequestMapping(value = "/getAllRentedBooks", method = RequestMethod.GET, produces = "application/json")
    public List<RentalRecord> getAllRentedBooks() {
        return reservationHistoryService.getRentedBooksList();
    }

    @RequestMapping(value = "/getAllClientRentedBooks/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<RentalRecord> getAllClientRentedBooks(@PathVariable("userId") Long userId) throws ReservationHistoryException, UserException {
        return reservationHistoryService.getRentedClientBooksList(userId);
    }

    @RequestMapping(value = "/findBooks", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Book> findBooks(@RequestBody SearchedBook searchedBook) throws BookException {
        return bookService.findBooks(searchedBook);
    }
}

