package com.example.emailservicespringboot.serviceImpl;

import com.example.emailservicespringboot.service.EmailService;
import com.example.emailservicespringboot.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service

public class EmailServiceImpl implements EmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String UTF_8_ENCODING = "UTF_8_ENCODING";
    public static final String UTF_8 = "UTF-8";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;



    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendSimpleMessage(String name, String to, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name,host,token));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    @Async
    public void sendMimeMessageWithAttachment(String name, String to, String token) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, token));


            String catFilePath = "/home/modestnerds/Downloads/email-service-spring-boot/src/main/java/com/example/emailservicespringboot/utils/images/cat.jpg";
            String docFilePath = "/home/modestnerds/Downloads/email-service-spring-boot/src/main/java/com/example/emailservicespringboot/utils/images/file.docx";


            FileSystemResource cat = new FileSystemResource(new File(catFilePath));
            if (cat.exists()) {
                helper.addAttachment(getContentId(cat.getFilename()), cat);
            } else {
                throw new RuntimeException("Attachment not found: " + catFilePath);
            }


            FileSystemResource file = new FileSystemResource(new File(docFilePath));
            if (file.exists()) {
                helper.addAttachment(getContentId(file.getFilename()), file);
            } else {
                throw new RuntimeException("Attachment not found: " + docFilePath);
            }

            mailSender.send(message);
        } catch (Exception e) {

            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }


    private MimeMessage getMimeMessage() {
        return mailSender.createMimeMessage();
    }

    private String getContentId(String fileName) {
        return "<" + fileName + ">";
    }



    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmailEmbeddedFiles(String name, String to, String token) {

    }
}
