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
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name,host,token));
            //add attachment
            FileSystemResource cat = new FileSystemResource(new File(System.getProperty("user.home")+ "/Downloads/images/cat.jpg"));
            FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home")+ "/Downloads/images/file.docx"));
            helper.addAttachment(getContentId(cat.getFilename()), cat);
            helper.addAttachment(getContentId(file.getFilename()), file);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    private MimeMessage getMimeMessage() {
        return mailSender.createMimeMessage();
    }

    private String getContentId(String fileName) {
        return "<" + fileName + ">";
    }

//    @Override
//    @Async
//    public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {
//        try{
//            MimeMessage message = getMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
//            helper.setPriority(1);
//            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
//            helper.setFrom(fromEmail);
//            helper.setTo(to);
//            helper.setText(EmailUtils.getEmailMessage(name,host,token));
//            //add attachment
//            FileSystemResource cat = new FileSystemResource(new File(System.getProperty("user.home")+ "/Downloads/images/cat.jpg"));
//            FileSystemResource file = new FileSystemResource(new File(System.getProperty("user.home")+ "/Downloads/images/file.docx"));
//            helper.addInline(cat.getFilename(), cat);
//            helper.addInline(file.getFilename(), file);
//            mailSender.send(message);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }

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
