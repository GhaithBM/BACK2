package com.example.PFE1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PFE1.model.Email;
import com.example.PFE1.service.EmailService;





@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:4200")  // Autorise les requêtes CORS depuis Angular
public class EmailController {

    @Autowired
    private EmailService emailService;

    // ✅ Envoi d'un email
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody Email email) {
        try {
            emailService.sendEmail(email.getTo(), email.getSubject(), email.getBody());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Email envoyé avec succès !");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de l'envoi de l'email : " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // ✅ Récupérer tous les emails
    @GetMapping("/getAllEmails")
    public ResponseEntity<List<Email>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }

    // ✅ Supprimer un email par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteEmail(@PathVariable Long id) {
        try {
            emailService.deleteEmail(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email supprimé avec succès !");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // ✅ Récupérer un email par ID
    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable Long id) {
        try {
            Email email = emailService.getEmailById(id);
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
