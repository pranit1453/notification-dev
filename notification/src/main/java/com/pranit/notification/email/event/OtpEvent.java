package com.pranit.notification.email.event;

import lombok.Builder;

import java.time.Duration;

@Builder
public record OtpEvent(
        String eventId,
        String email,
        String otp,
        Duration expiresAt
) {
}
