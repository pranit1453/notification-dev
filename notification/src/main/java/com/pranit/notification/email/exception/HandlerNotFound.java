package com.pranit.notification.email.exception;

import org.springframework.http.HttpStatus;

public class HandlerNotFound extends BaseException {
    public HandlerNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
