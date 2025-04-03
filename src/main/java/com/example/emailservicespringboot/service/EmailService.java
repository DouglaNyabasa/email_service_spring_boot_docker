package com.example.emailservicespringboot.service;

public interface EmailService {

    void sendSimpleMessage(String name, String to, String token);
    void sendMessageWithAttachment(String name, String to, String token);
    void sendMessageWithEmbeddedImages(String name, String to, String token);
    void sendMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailEmbeddedFiles(String name, String to, String token);
}
