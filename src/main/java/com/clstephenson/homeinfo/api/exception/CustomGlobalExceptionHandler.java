package com.clstephenson.homeinfo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Let Spring handle the exception, we just override the status code
    @ExceptionHandler({
            UserNotFoundException.class,
            PropertyNotFoundException.class,
            VendorNotFoundException.class,
            TaskNotFoundException.class,
            IdeaNotFoundException.class,
            LocationNotFoundException.class,
            StoredFileNotFoundException.class,
            FeatureNotFoundException.class
    })
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
