package com.example.emailservicespringboot.service;

public interface EmailService {

    void sendSimpleMessage(String name, String to, String token);
    void sendMimeMessageWithAttachment(String name, String to, String token);
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailEmbeddedFiles(String name, String to, String token);
}
