package restlibrary.exception.rest;


public class RestLibraryError {

    private int status;
    private String errorMessage;
    private String url;

    public RestLibraryError(int status, String errorMessage, String url) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.url = url;
    }

    public RestLibraryError() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
