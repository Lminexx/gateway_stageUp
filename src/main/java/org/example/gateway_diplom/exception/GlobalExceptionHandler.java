package org.example.gateway_diplom.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = WebClientException.class)
    public ResponseEntity<ErrorMessage> handleHttpClientError(WebClientException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatusCode());
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ErrorMessage> handleHttpClientErrorException(HttpClientErrorException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatusCode());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<ErrorMessage> handleHttpServerErrorException(HttpServerErrorException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatusCode());
    }
}
