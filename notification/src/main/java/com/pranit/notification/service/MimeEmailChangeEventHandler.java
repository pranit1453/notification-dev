package com.pranit.notification.service;

import com.pranit.notification.enums.MailType;
import com.pranit.notification.enums.NotificationCode;
import com.pranit.notification.event.OtpEvent;
import com.pranit.notification.mail.MailRequest;
import com.pranit.notification.factory.MailSenderFactory;
import com.pranit.notification.strategy.notification.NotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public final class MimeEmailChangeEventHandler implements NotificationHandler<OtpEvent> {

    private final static NotificationCode CODE = NotificationCode.EMAIL_CHANGE_OTP;
    private final MailSenderFactory mailSenderFactory;

    @Override
    public void sendNotification(OtpEvent otpEvent, MailType mailType) {
        processEmail(otpEvent, mailType);
    }

    private void processEmail(OtpEvent data, MailType mailType) {
        log.info("Processing email change eventId: {}", data.eventId());
        Map<String, Object> vars = new LinkedHashMap<>();
        vars.put("otp", data.otp());
        vars.put("expiresIn", data.expiresAt());
        MailRequest request = MailRequest.builder()
                .to(data.email())
                .subject("Email Change Verification OTP")
                .templateName("email-change-otp")
                .variables(vars)
                .build();
        mailSenderFactory.getMailSender(mailType).send(request);
    }

    @Override
    public NotificationCode getNotificationCode() {
        return CODE;
    }
}
