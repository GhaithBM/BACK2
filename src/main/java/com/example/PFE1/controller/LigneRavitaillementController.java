package com.example.PFE1.controller;

import com.example.PFE1.model.LigneRavitaillement;
import com.example.PFE1.service.ILigneRavitaillementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligne_ravitaillements")
@CrossOrigin(origins = "http://localhost:4200")
public class LigneRavitaillementController {

    @Autowired
    private ILigneRavitaillementService ligneRavitaillementService;

    // Créer une ligne Ravitaillement
    @PostMapping
    public ResponseEntity<LigneRavitaillement> createLigneRavitaillement(@RequestBody LigneRavitaillement ligneRavitaillement) {
        LigneRavitaillement savedLigneRavitaillement = ligneRavitaillementService.saveLigneRavitaillement(ligneRavitaillement);
        return new ResponseEntity<>(savedLigneRavitaillement, HttpStatus.CREATED);
    }

    // Récupérer toutes les lignes Ravitaillement
    @GetMapping
    public ResponseEntity<List<LigneRavitaillement>> getAllLigneRavitaillements() {
        List<LigneRavitaillement> ligneRavitaillements = ligneRavitaillementService.getAllLigneRavitaillements();
        return new ResponseEntity<>(ligneRavitaillements, HttpStatus.OK);
    }

    // Récupérer une ligne Ravitaillement par ID
    @GetMapping("/{id}")
    public ResponseEntity<LigneRavitaillement> getLigneRavitaillementById(@PathVariable Long id) {
        LigneRavitaillement ligneRavitaillement = ligneRavitaillementService.getLigneRavitaillementById(id);
        return new ResponseEntity<>(ligneRavitaillement, HttpStatus.OK);
    }

    // Mettre à jour une ligne Ravitaillement
    @PutMapping("/{id}")
    public ResponseEntity<LigneRavitaillement> updateLigneRavitaillement(@PathVariable Long id, @RequestBody LigneRavitaillement ligneRavitaillement) {
        LigneRavitaillement updatedLigneRavitaillement = ligneRavitaillementService.updateLigneRavitaillement(id, ligneRavitaillement);
        return new ResponseEntity<>(updatedLigneRavitaillement, HttpStatus.OK);
    }

    // Supprimer une ligne Ravitaillement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneRavitaillement(@PathVariable Long id) {
        ligneRavitaillementService.deleteLigneRavitaillement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
