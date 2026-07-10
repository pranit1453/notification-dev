package com.pranit.notification.email.pattern.stratergy.mail;

import com.pranit.notification.email.enums.MailType;
import com.pranit.notification.email.exception.MailNotSent;
import com.pranit.notification.email.mail.Attachment;
import com.pranit.notification.email.mail.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
public final class MimeMailSenderImpl implements MailSender {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${mail.from}")
    private String from;

    @Override
    public void send(MailRequest request) {
        try {
            log.info("Sending email to {}", request.getTo());
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = getMimeMessageHelper(request, message);

            if (request.getAttachments() != null
                    && !request.getAttachments().isEmpty()) {
                for (Attachment attachment : request.getAttachments()) {
                    helper.addAttachment(
                            attachment.fileName(),
                            new ByteArrayResource(attachment.data()),
                            attachment.contentType()
                    );
                }
            }
            javaMailSender.send(message);
            log.info("Email sent successfully to {}", request.getTo());
        } catch (MessagingException e) {
            log.error("Failed to send mail to {}", request.getTo(), e);
            throw new MailNotSent("Failed to send email");
        }
    }

    @NonNull
    private MimeMessageHelper getMimeMessageHelper
            (final MailRequest request, final MimeMessage message)
            throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                "UTF-8"
        );
        helper.setFrom(from);
        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        String content;

        if (request.getTemplateName() != null) {
            Context context = new Context();
            if (request.getVariables() != null) {
                request.getVariables().forEach(context::setVariable);
            }
            context.setVariable("useSignature", request.isUseSignature());
            content = templateEngine.process(request.getTemplateName(), context);
            helper.setText(content, true);
        } else {
            content = request.getBody();
            helper.setText(content, false);
        }
        return helper;
    }

    @Override
    public MailType getMailType() {
        return MailType.MIME;
    }
}
