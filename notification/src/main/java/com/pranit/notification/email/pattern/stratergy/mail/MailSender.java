package com.pranit.notification.email.pattern.stratergy.mail;


import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.mail.MailRequest;

public interface MailSender {

    void send(MailRequest request);

    MailType getMailType();
}
