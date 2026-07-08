package com.pranit.notification.email.exception;

import org.springframework.http.HttpStatus;

public class SenderNotFound extends BaseException {
    public SenderNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
