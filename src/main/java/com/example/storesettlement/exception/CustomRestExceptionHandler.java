package com.example.storesettlement.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, 3000,
                ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 2000, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex, final WebRequest request) {
        final String error = "Check your password";

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 1000, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
        String message = ex.getLocalizedMessage();
        final String error = message.substring(message.indexOf("propertyPath=") + 13, message.indexOf(", rootBeanClass")) + " 데이터가 "
                + message.substring(message.indexOf("interpolatedMessage='") + 21, message.indexOf("', propertyPath="));

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 5000, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(final IllegalStateException ex, final WebRequest request) {
        final String error = ex.getLocalizedMessage();

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 6000, "Member already exists", error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.OK);
    }
}
