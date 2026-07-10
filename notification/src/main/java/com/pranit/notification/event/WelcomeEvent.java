package com.pranit.notification.event;

import lombok.Builder;

@Builder
public record WelcomeEvent(
        String eventId,
        String username,
        String email
) {
}
