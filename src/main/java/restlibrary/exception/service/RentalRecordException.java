package restlibrary.exception.service;


public class RentalRecordException extends Exception  {

    private static final long serialVersionUID = -6260558968429115231L;

    public RentalRecordException(String errorMessage) {
        super(errorMessage);
    }
}
