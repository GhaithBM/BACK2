package com.example.PFE1.controller;

import com.example.PFE1.model.LigneFeuille;
import com.example.PFE1.service.ILigneFeuilleService;
import com.example.PFE1.service.Implementation.LigneFeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligne_feuille")
@CrossOrigin(origins = "http://localhost:4200")

public class LigneFeuilleController {

    @Autowired
    private ILigneFeuilleService ligneFeuilleService;

    @PostMapping
    public ResponseEntity<LigneFeuille> createLigneFeuille(@RequestBody LigneFeuille ligneFeuille) {
        LigneFeuille savedLigneFeuille = ligneFeuilleService.saveLigneFeuille(ligneFeuille);
        return new ResponseEntity<>(savedLigneFeuille, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LigneFeuille>> getAllLigneFeuilles() {
        List<LigneFeuille> ligneFeuilles = ligneFeuilleService.getAllLigneFeuilles();
        return new ResponseEntity<>(ligneFeuilles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneFeuille> getLigneFeuilleById(@PathVariable Long id) {
        LigneFeuille ligneFeuille = ligneFeuilleService.getLigneFeuilleById(id);
        return new ResponseEntity<>(ligneFeuille, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneFeuille> updateLigneFeuille(@PathVariable Long id, @RequestBody LigneFeuille ligneFeuille) {
        LigneFeuille updatedLigneFeuille = ligneFeuilleService.updateLigneFeuille(id, ligneFeuille);
        return new ResponseEntity<>(updatedLigneFeuille, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneFeuille(@PathVariable Long id) {
        ligneFeuilleService.deleteLigneFeuille(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
