package org.example.gateway_diplom.exception;

import lombok.Data;

@Data
public class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
