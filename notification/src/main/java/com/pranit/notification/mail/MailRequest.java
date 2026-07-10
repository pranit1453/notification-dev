package com.pranit.notification.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public final class MailRequest {
    private String to;
    private String subject;
    private String body;
    private String templateName;
    private Map<String, Object> variables;
    private List<Attachment> attachments;
    @Builder.Default
    private boolean useSignature = true;
}
