package com.example.PFE1.service.Implementation;


import com.example.PFE1.model.Email;
import com.example.PFE1.repository.IEmailRepo;
import com.example.PFE1.service.EmailService;




import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IEmailRepo emailRepository;

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            // Envoi de l'email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // HTML autorisé
            mailSender.send(message);

            // Sauvegarde dans la base
            Email email = new Email(to, subject, body);
            emailRepository.save(email);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Ici tu peux également logger l'erreur ou lever une exception personnalisée
        }
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @Override
    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }

    @Override
    public Email getEmailById(Long id) {
        Optional<Email> optionalEmail = emailRepository.findById(id);
        return optionalEmail.orElseThrow(() ->
                new RuntimeException("Email introuvable avec l'id : " + id));
    }
}
