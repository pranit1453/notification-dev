package com.pranit.notification.email.event;

import lombok.Builder;

@Builder
public record OtpEvent(
        String eventId,
        String email,
        String otp
) {
}
