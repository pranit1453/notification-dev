package com.pranit.notification.email.mail;

import lombok.Builder;

@Builder
public record Attachment(
        String fileName,
        byte[] data,
        String contentType
) {
}
