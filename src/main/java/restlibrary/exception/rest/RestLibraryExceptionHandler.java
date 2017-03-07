package restlibrary.exception.rest;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestLibraryExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RestLibraryError exception(HttpServletRequest req, Throwable ex) {
        return new RestLibraryError(ex.getMessage(), ex.getClass().getName());
    }
}
