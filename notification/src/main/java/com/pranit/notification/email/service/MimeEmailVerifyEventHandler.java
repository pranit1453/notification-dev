package com.pranit.notification.email.service;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.event.OtpEvent;
import com.pranit.notification.email.mail.MailRequest;
import com.pranit.notification.email.pattern.factory.MailSenderFactory;
import com.pranit.notification.email.pattern.stratergy.notification.NotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public final class MimeEmailVerifyEventHandler implements NotificationHandler<OtpEvent> {

    private final static NotificationCode CODE = NotificationCode.EMAIL_VERIFY_OTP;
    private final MailSenderFactory mailSenderFactory;

    @Override
    public void sendNotification(OtpEvent data, MailType mailType) {
        processEmail(data, mailType);
    }

    private void processEmail(OtpEvent data, MailType mailType) {
        log.info("Processing email verify eventId: {}", data.eventId());

        Map<String, Object> vars = new LinkedHashMap<>();
        vars.put("otp", data.otp());
        MailRequest request = MailRequest.builder()
                .to(data.email())
                .subject("Email Change Verification OTP")
                .templateName("email-verify")
                .variables(vars)
                .build();

        mailSenderFactory.getMailSender(mailType).send(request);
    }

    @Override
    public NotificationCode getNotificationCode() {
        return CODE;
    }

}
