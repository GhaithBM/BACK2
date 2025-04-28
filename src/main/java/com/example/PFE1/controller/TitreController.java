package com.example.PFE1.controller;

import com.example.PFE1.model.Titre;
import com.example.PFE1.service.ITitreService;
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
@RequestMapping("/api/titres")
public class TitreController {

    @Autowired
    private ITitreService titreService;

    @PostMapping("/createTitre")
    // http://localhost:8080/api/titres
    public ResponseEntity<Titre> createTitre(@RequestBody Titre titre) {
        Titre savedTitre = titreService.createTitre(titre);
        return new ResponseEntity<>(savedTitre, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    // http://localhost:8080/api/titres/1
    public ResponseEntity<Titre> getTitreById(@PathVariable("id") Long titreId) {
        Titre titre = titreService.getTitreById(titreId);
        return new ResponseEntity<>(titre, HttpStatus.OK);
    }

    @GetMapping("/getAllTitres")
    // http://localhost:8080/api/titres
    public ResponseEntity<List<Titre>> getAllTitres() {
        List<Titre> titreList = titreService.getAllTitres();
        return new ResponseEntity<>(titreList, HttpStatus.OK);
    }

    @PutMapping("/updateTitre/{id}")
    // http://localhost:8080/api/titres/1
    public ResponseEntity<Titre> updateTitre(@PathVariable("id") Long titreId, @RequestBody Titre titre) {
        titre.setId(titreId);
        Titre updatedTitre = titreService.updateTitre(titre);
        return new ResponseEntity<>(updatedTitre, HttpStatus.OK);
    }


    @DeleteMapping("/deleteTitre/{id}")
    public ResponseEntity<?> deleteTitre(@PathVariable Long id) {
        try {
            titreService.deleteTitre(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Titre supprimé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @PostMapping("/Search")
    public ResponseEntity<List<Titre>> SearchTitre(@RequestBody Titre titre){
        List<Titre> titreList = titreService.SearchFilter(titre);
        return new ResponseEntity<>(titreList, HttpStatus.OK);

    }
}
