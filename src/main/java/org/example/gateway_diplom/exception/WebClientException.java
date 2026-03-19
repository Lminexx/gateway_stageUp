package org.example.gateway_diplom.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class WebClientException extends RuntimeException {
    private final HttpStatusCode statusCode;

    public WebClientException(HttpStatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
