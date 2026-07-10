package com.pranit.notification.strategy.notification;

import com.pranit.notification.enums.MailType;
import com.pranit.notification.enums.NotificationCode;

public interface NotificationHandler<T> {

    void sendNotification(T event, MailType mailType);

    NotificationCode getNotificationCode();
}
