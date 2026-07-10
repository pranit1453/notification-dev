package com.pranit.notification.email.subscriber;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.event.OtpEvent;
import com.pranit.notification.email.event.SecurityEvent;
import com.pranit.notification.email.event.WelcomeEvent;
import com.pranit.notification.email.factory.NotificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class EventSubscriberService {
    private final NotificationFactory notificationFactory;

    @Bean
    public Consumer<OtpEvent> registrationConsumer() {
        final NotificationCode CODE = NotificationCode.REGISTRATION_OTP;
        return event -> notificationFactory
                .<OtpEvent>getNotificationHandler(CODE)
                .sendNotification(event, MailType.MIME);
    }

    @Bean
    public Consumer<OtpEvent> passwordChangeConsumer() {
        final NotificationCode CODE = NotificationCode.PASSWORD_CHANGE_OTP;
        return event -> notificationFactory
                .<OtpEvent>getNotificationHandler(CODE)
                .sendNotification(event, MailType.MIME);
    }

    @Bean
    public Consumer<OtpEvent> verifyEmailConsumer() {
        final NotificationCode CODE = NotificationCode.EMAIL_VERIFY_OTP;
        return event -> {
            notificationFactory
                    .<OtpEvent>getNotificationHandler(CODE)
                    .sendNotification(event, MailType.MIME);
        };
    }

    @Bean
    public Consumer<OtpEvent> emailChangeConsumer() {
        final NotificationCode CODE = NotificationCode.EMAIL_CHANGE_OTP;
        return event -> notificationFactory
                .<OtpEvent>getNotificationHandler(CODE)
                .sendNotification(event, MailType.MIME);
    }

    @Bean
    public Consumer<SecurityEvent> securityAlertConsumer() {
        final NotificationCode CODE = NotificationCode.SECURITY_ALERT;
        return event -> notificationFactory
                .<SecurityEvent>getNotificationHandler(CODE)
                .sendNotification(event, MailType.MIME);
    }

    @Bean
    public Consumer<WelcomeEvent> welcomeUserConsumer() {
        final NotificationCode CODE = NotificationCode.WELCOME_USER;
        return event -> notificationFactory
                .<WelcomeEvent>getNotificationHandler(CODE)
                .sendNotification(event, MailType.MIME);
    }
}
