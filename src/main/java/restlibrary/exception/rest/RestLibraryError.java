package restlibrary.exception.rest;

import java.io.Serializable;


public class RestLibraryError implements Serializable {

    private static final long serialVersionUID = -5306088220288021507L;

    private String errorMessage;
    private String exceptionClass;

    public RestLibraryError(String errorMessage, String exceptionClass) {
        this.errorMessage = errorMessage;
        this.exceptionClass = exceptionClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }
}
