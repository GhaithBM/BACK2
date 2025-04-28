package com.example.PFE1.controller;


import com.example.PFE1.model.Fonction;
import com.example.PFE1.service.IFonctionService;
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
@RequestMapping("/api/fonctions")
public class FonctionController {

    @Autowired
    private IFonctionService fonctionService;

    @PostMapping("/createFonction")
    // http://localhost:8080/api/fonctions
    public ResponseEntity<Fonction> createFonction(@RequestBody Fonction fonction) {
        Fonction savedFonction = fonctionService.createFonction(fonction);
        return new ResponseEntity<>(savedFonction, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    // http://localhost:8080/api/fonctions/1
    public ResponseEntity<Fonction> getFonctionById(@PathVariable("id") Long id) {
        Fonction fonction = fonctionService.getFonctionById(id);
        return new ResponseEntity<>(fonction, HttpStatus.OK);
    }

    @GetMapping("/getAllFonctions")
    // http://localhost:8080/api/fonctions
    public ResponseEntity<List<Fonction>> getAllFonctions() {
        List<Fonction> fonctionList = fonctionService.getAllFonctions();
        return new ResponseEntity<>(fonctionList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    // http://localhost:8080/api/fonctions/1
    public ResponseEntity<Fonction> updateFonction(@PathVariable("id") Long id, @RequestBody Fonction fonction) {
        fonction.setId(id);
        Fonction updatedFonction = fonctionService.updateFonction(fonction);
        return new ResponseEntity<>(updatedFonction, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFonction/{id}")
    public ResponseEntity<?> deleteFonction(@PathVariable Long id) {
        try {
            fonctionService.deleteFonction(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Fonction supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
//    @PostMapping("/search")
//    public ResponseEntity<List<Fonction>> SearchFilter(@RequestBody Fonction fonction) {
//        List<Fonction> fonctionList = fonctionService.SearchFilter(fonction);
//        return new ResponseEntity<>(fonctionList, HttpStatus.OK);
//    }

    @PostMapping("/search")
    public ResponseEntity<List<Fonction>> searchFonctions(@RequestBody Fonction fonction) {
        List<Fonction> fonctions = fonctionService.searchFonctions(fonction);
        return new ResponseEntity<>(fonctions, HttpStatus.OK);
    }
}
