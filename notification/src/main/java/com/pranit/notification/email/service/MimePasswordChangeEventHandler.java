package com.pranit.notification.email.service;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.event.OtpEvent;
import com.pranit.notification.email.mail.MailRequest;
import com.pranit.notification.email.pattern.factory.MailSenderFactory;
import com.pranit.notification.email.pattern.stratergy.notification.NotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public final class MimePasswordChangeEventHandler implements NotificationHandler<OtpEvent> {

    private final static NotificationCode CODE = NotificationCode.PASSWORD_CHANGE_OTP;
    private final MailSenderFactory mailSenderFactory;

    @Override
    public void sendNotification(OtpEvent otpEvent, MailType mailType) {
        processEmail(otpEvent, mailType);
    }

    private void processEmail(OtpEvent data, MailType mailType) {
        log.info("Processing password change email eventId: {}", data.eventId());

        Map<String, Object> vars = new LinkedHashMap<>();
        vars.put("otp", data.otp());
        MailRequest request = MailRequest.builder()
                .to(data.email())
                .subject("Password Change Verification OTP")
                .templateName("password-change-otp")
                .variables(vars)
                .build();

        mailSenderFactory.getMailSender(mailType).send(request);
    }

    @Override
    public NotificationCode getNotificationCode() {
        return CODE;
    }
}
