package com.pranit.notification.email.service;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.event.SecurityEvent;
import com.pranit.notification.email.mail.MailRequest;
import com.pranit.notification.email.mask.MaskData;
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
public class MimeSecurityAlertEventHandler implements NotificationHandler<SecurityEvent> {

    private final static NotificationCode CODE = NotificationCode.SECURITY_ALERT;
    private final MailSenderFactory mailSenderFactory;

    @Override
    public void sendNotification(SecurityEvent event, MailType mailType) {
        processEmail(event, mailType);
    }

    private void processEmail(SecurityEvent data, MailType mailType) {
        log.info("Processing security alert email eventId: {}", data.eventId());
        String oldEmail = MaskData.maskEmail(data.oldMail());
        String newEmail = MaskData.maskEmail(data.newMail());
        Map<String, Object> vars = new LinkedHashMap<>();
        vars.put("oldMail", oldEmail);
        vars.put("newMail", newEmail);

        MailRequest request = MailRequest.builder()
                .to(data.oldMail())
                .subject("Security Alert: Email Address Changed")
                .templateName("security-alert")
                .variables(vars)
                .useSignature(true)
                .build();

        mailSenderFactory.getMailSender(mailType)
                .send(request);
    }

    @Override
    public NotificationCode getNotificationCode() {
        return CODE;
    }
}
