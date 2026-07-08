package com.pranit.notification.email.event;

import lombok.Builder;

@Builder
public record WelcomeEvent(
        String eventId,
        String username,
        String email
) {
}
