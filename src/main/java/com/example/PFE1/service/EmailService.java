package com.example.PFE1.service;



import java.util.List;

import com.example.PFE1.model.Email;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    List<Email> getAllEmails();
    void deleteEmail(Long id);
    Email getEmailById(Long id);
}

