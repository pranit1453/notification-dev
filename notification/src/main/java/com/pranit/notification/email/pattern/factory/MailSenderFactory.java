package com.pranit.notification.email.pattern.factory;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.exception.SenderNotFound;
import com.pranit.notification.email.pattern.stratergy.mail.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MailSenderFactory {
    private final Map<MailType, MailSender> senderFactory;

    public MailSenderFactory(List<MailSender> mailSenders) {
        this.senderFactory = mailSenders.stream()
                .collect(Collectors.toMap(
                        MailSender::getMailType,
                        mailSender -> mailSender));
    }

    public MailSender getMailSender(MailType mailType) {
        MailSender sender = senderFactory.get(mailType);
        if (sender == null) {
            log.warn("Mail sender not found for mailType: {}", mailType);
            throw new SenderNotFound("No Sender found");
        }
        return sender;
    }
}
