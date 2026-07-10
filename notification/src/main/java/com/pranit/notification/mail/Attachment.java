package com.pranit.notification.mail;

import lombok.Builder;

@Builder
public record Attachment(
        String fileName,
        byte[] data,
        String contentType
) {
}
