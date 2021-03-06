package restlibrary.exception.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import restlibrary.exception.service.BookException;
import restlibrary.exception.service.RentalRecordException;
import restlibrary.exception.service.RentalRecordHistoryException;
import restlibrary.exception.service.UserException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestLibraryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserException.class})
    public ResponseEntity<RestLibraryError> handleUserException(UserException ex, HttpServletRequest req) {
        RestLibraryError restLibraryError = new RestLibraryError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURL().toString());
        return new ResponseEntity<>(restLibraryError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookException.class})
    public ResponseEntity<RestLibraryError> handleBookException(BookException ex, HttpServletRequest req) {
        RestLibraryError restLibraryError = new RestLibraryError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURL().toString());
        return new ResponseEntity<>(restLibraryError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RentalRecordHistoryException.class})
    public ResponseEntity<RestLibraryError> handleReservationHistoryException(RentalRecordHistoryException ex, HttpServletRequest req) {
        RestLibraryError restLibraryError = new RestLibraryError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURL().toString());
        return new ResponseEntity<>(restLibraryError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RentalRecordException.class})
    public ResponseEntity<RestLibraryError> handleReservationException(RentalRecordException ex, HttpServletRequest req) {
        RestLibraryError restLibraryError = new RestLibraryError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), req.getRequestURL().toString());
        return new ResponseEntity<>(restLibraryError, HttpStatus.BAD_REQUEST);
    }
}
