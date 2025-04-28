package com.example.PFE1.controller;

import com.example.PFE1.model.Ligne;
import com.example.PFE1.service.ILigneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/lignes")
public class LigneController {

    @Autowired
    private ILigneService ligneService;

    @PostMapping("/createLigne")
    // http://localhost:8080/api/lignes
    public ResponseEntity<Ligne> createLigne(@RequestBody Ligne ligne) {
        Ligne savedLigne = ligneService.createLigne(ligne);
        return new ResponseEntity<>(savedLigne, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    // http://localhost:8080/api/lignes/1
    public ResponseEntity<Ligne> getLigneById(@PathVariable("id") Long id) {
        Ligne ligne = ligneService.getLigneById(id);
        return new ResponseEntity<>(ligne, HttpStatus.OK);
    }

    @GetMapping("/getAllLignes")
    // http://localhost:8080/api/lignes
    public ResponseEntity<List<Ligne>> getAllLignes() {
        List<Ligne> ligneList = ligneService.getAllLignes();
        return new ResponseEntity<>(ligneList, HttpStatus.OK);
    }

    @PutMapping("/updateLigne/{id}")
    // http://localhost:8080/api/lignes/1
    public ResponseEntity<Ligne> updateLigne(@PathVariable("id") Long id, @RequestBody Ligne ligne) {
        ligne.setId(id);
        Ligne updatedLigne = ligneService.updateLigne(ligne);
        return new ResponseEntity<>(updatedLigne, HttpStatus.OK);
    }

    @DeleteMapping("/deleteLigne/{id}")
    public ResponseEntity<?> deleteLigne(@PathVariable Long id) {
        try {
            ligneService.deleteLigne(id); // Suppression de la ligne via le service Ligne
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Ligne supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @PostMapping("/Search")
    public ResponseEntity<List<Ligne>>Search (@RequestBody Ligne ligne) {
        List<Ligne> ligneList = ligneService.SearchFilter(ligne);
        return new ResponseEntity<>(ligneList, HttpStatus.OK);
    }
}