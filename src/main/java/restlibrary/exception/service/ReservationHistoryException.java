package restlibrary.exception.service;


public class ReservationHistoryException extends Exception {

    private static final long serialVersionUID = -6260558968429115131L;

    public ReservationHistoryException(String errorMessage) {
        super(errorMessage);
    }
}
