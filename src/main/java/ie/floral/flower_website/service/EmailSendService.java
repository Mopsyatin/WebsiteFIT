package ie.floral.flower_website.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSendService {
    void sendContactEmail(String email, String subject, String body);
}