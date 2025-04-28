package com.example.PFE1.controller;

import com.example.PFE1.model.Ravitaillement;
import com.example.PFE1.service.IRavitaillementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ravitaillements")
@CrossOrigin(origins = "http://localhost:4200")
public class RavitaillementController {

    @Autowired
    private IRavitaillementService ravitaillementService;

    // Récupérer tous les Ravitaillements
    @GetMapping
    public List<Ravitaillement> getAllRavitaillements(
            @RequestParam(required = false) Integer matricule,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String fonction,
            @RequestParam(required = false) String affectation,
            @RequestParam(required = false) String codeDistrict,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam(required = false) String sequence) {

        // Implémentez la logique de filtrage ici
        return ravitaillementService.getRavitaillementsByCriteria(matricule, nom, fonction, affectation, codeDistrict, date, sequence);
    }

    // Récupérer un Ravitaillement par ID
    @GetMapping("/{id}")
    public Ravitaillement getRavitaillementById(@PathVariable Long id) {
        return ravitaillementService.getRavitaillementById(id);
    }

    // Mettre à jour un Ravitaillement
    @PutMapping("/{id}")
    public Ravitaillement updateRavitaillement(@PathVariable Long id, @RequestBody Ravitaillement ravitaillement) {
        return ravitaillementService.updateRavitaillement(id, ravitaillement);
    }

    // Supprimer un Ravitaillement
    @DeleteMapping("/{id}")
    public void deleteRavitaillement(@PathVariable Long id) {
        ravitaillementService.deleteRavitaillement(id);
    }

    // Créer un Ravitaillement
    @PostMapping
    public ResponseEntity<Ravitaillement> createRavitaillement(@RequestBody Ravitaillement ravitaillement) {
        try {
            Ravitaillement savedRavitaillement = ravitaillementService.saveRavitaillement(ravitaillement);
            return new ResponseEntity<>(savedRavitaillement, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log l'erreur
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Autocomplétion des matricules disponibles
    @GetMapping("/autocomplete/matricules")
    public List<Integer> autocompleteMatricules() {
        return ravitaillementService.getAllMatricules();
    }

    // Autocomplétion des codes district disponibles
    @GetMapping("/autocomplete/districts")
    public List<String> autocompleteCodesDistrict() {
        return ravitaillementService.getAllCodesDistrict();
    }
}
