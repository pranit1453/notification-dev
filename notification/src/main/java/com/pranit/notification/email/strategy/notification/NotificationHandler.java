package com.pranit.notification.email.strategy.notification;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.enums.NotificationCode;

public interface NotificationHandler<T> {

    void sendNotification(T event, MailType mailType);

    NotificationCode getNotificationCode();
}
