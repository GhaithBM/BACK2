package com.example.PFE1.controller;

import com.example.PFE1.model.LigneBcn;
import com.example.PFE1.service.ILigneBcnService;
import com.example.PFE1.service.Implementation.LigneBcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligne_bcns")
@CrossOrigin(origins = "http://localhost:4200")
public class LigneBcnController {

    @Autowired
    private ILigneBcnService ligneBcnService;

    // Créer une ligne BCN
    @PostMapping
    public ResponseEntity<LigneBcn> createLigneBcn(@RequestBody LigneBcn ligneBcn) {
        LigneBcn savedLigneBcn = ligneBcnService.saveLigneBcn(ligneBcn);
        return new ResponseEntity<>(savedLigneBcn, HttpStatus.CREATED);
    }

    // Récupérer toutes les lignes BCN
    @GetMapping
    public ResponseEntity<List<LigneBcn>> getAllLigneBcns() {
        List<LigneBcn> ligneBcns = ligneBcnService.getAllLigneBcns();
        return new ResponseEntity<>(ligneBcns, HttpStatus.OK);
    }

    // Récupérer une ligne BCN par ID
    @GetMapping("/{id}")
    public ResponseEntity<LigneBcn> getLigneBcnById(@PathVariable Long id) {
        LigneBcn ligneBcn = ligneBcnService.getLigneBcnById(id);
        return new ResponseEntity<>(ligneBcn, HttpStatus.OK);
    }

    // Mettre à jour une ligne BCN
    @PutMapping("/{id}")
    public ResponseEntity<LigneBcn> updateLigneBcn(@PathVariable Long id, @RequestBody LigneBcn ligneBcn) {
        LigneBcn updatedLigneBcn = ligneBcnService.updateLigneBcn(id, ligneBcn);
        return new ResponseEntity<>(updatedLigneBcn, HttpStatus.OK);
    }

    // Supprimer une ligne BCN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneBcn(@PathVariable Long id) {
        ligneBcnService.deleteLigneBcn(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update-debut")
    public ResponseEntity<LigneBcn> updateDebutLigneBcn(@PathVariable Long id, @RequestBody int nouveauDebut) {
        LigneBcn ligneBcn = ligneBcnService.getLigneBcnById(id);
        if (ligneBcn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ligneBcn.setDebut(nouveauDebut);
        LigneBcn updated = ligneBcnService.saveLigneBcn(ligneBcn);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
