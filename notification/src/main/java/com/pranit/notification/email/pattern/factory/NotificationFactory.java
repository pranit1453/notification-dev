package com.pranit.notification.email.pattern.factory;

import com.pranit.notification.email.enums.NotificationCode;
import com.pranit.notification.email.exception.HandlerNotFound;
import com.pranit.notification.email.pattern.stratergy.notification.NotificationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public final class NotificationFactory {

    private final Map<NotificationCode, NotificationHandler<?>> notificationFactory;

    public NotificationFactory(List<NotificationHandler<?>> handlers) {
        this.notificationFactory = handlers.stream()
                .collect(Collectors.toMap(
                        NotificationHandler::getNotificationCode,
                        handler -> handler));
    }

    @SuppressWarnings("unchecked")
    public <T> NotificationHandler<T> getNotificationHandler(NotificationCode code) {
        NotificationHandler<T> handler = (NotificationHandler<T>) notificationFactory.get(code);
        if (handler == null) {
            log.warn("No handler for code {}", code);
            throw new HandlerNotFound("Handler not found");
        }
        return handler;
    }
}
