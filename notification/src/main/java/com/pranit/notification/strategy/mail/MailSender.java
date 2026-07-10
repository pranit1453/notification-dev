package com.pranit.notification.strategy.mail;


import com.pranit.notification.enums.MailType;
import com.pranit.notification.mail.MailRequest;

public interface MailSender {

    void send(MailRequest request);

    MailType getMailType();
}
