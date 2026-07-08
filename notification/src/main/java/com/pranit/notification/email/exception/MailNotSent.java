package com.pranit.notification.email.exception;

import org.springframework.http.HttpStatus;

public class MailNotSent extends BaseException {
    public MailNotSent(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
