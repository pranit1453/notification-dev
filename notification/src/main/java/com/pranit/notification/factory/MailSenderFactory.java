<<<<<<<< HEAD:notification/src/main/java/com/pranit/notification/email/factory/MailSenderFactory.java
package com.pranit.notification.email.factory;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.exception.SenderNotFound;
import com.pranit.notification.email.strategy.mail.MailSender;
========
package com.pranit.notification.factory;

import com.pranit.notification.enums.MailType;
import com.pranit.notification.exception.SenderNotFound;
import com.pranit.notification.strategy.mail.MailSender;
>>>>>>>> 41d1e61 (Complete implementation of notification service with rabbitMQ):notification/src/main/java/com/pranit/notification/factory/MailSenderFactory.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public final class MailSenderFactory {
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
