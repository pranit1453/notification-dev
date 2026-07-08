package com.pranit.notification.email.service;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.event.WelcomeEvent;
import com.pranit.notification.email.mail.MailRequest;
import com.pranit.notification.email.mask.MaskData;
import com.pranit.notification.email.pattern.factory.MailSenderFactory;
import com.pranit.notification.email.pattern.stratergy.notification.NotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MimeWelcomeUserEventHandler implements NotificationHandler<WelcomeEvent> {

    private final static NotificationCode CODE = NotificationCode.WELCOME_USER;
    private final MailSenderFactory mailSenderFactory;

    @Override
    public void sendNotification(WelcomeEvent event, MailType mailType) {
        processEmail(event, mailType);
    }

    private void processEmail(WelcomeEvent data, MailType mailType) {
        log.info("Processing welcome email eventId: {}", data.eventId());

        Map<String, Object> vars = new HashMap<>();
        vars.put("username", data.username());
        vars.put("email", MaskData.maskEmail(data.email()));

        MailRequest request = MailRequest.builder()
                .to(data.email())
                .subject("Welcome to Our Platform 🎉")
                .templateName("welcome-email")
                .variables(vars)
                .useSignature(true)
                .build();
        log.info("Mail Request: {}", request);
        mailSenderFactory.getMailSender(mailType)
                .send(request);
    }

    @Override
    public NotificationCode getNotificationCode() {
        return CODE;
    }
}
