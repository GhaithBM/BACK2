package com.example.PFE1.controller;

import com.example.PFE1.model.Bcn;
import com.example.PFE1.service.IBcnService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bcns")
public class BcnController {

    @Autowired
    private IBcnService bcnService;

    // Récupérer tous les BCNs
    @GetMapping
    public List<Bcn> getAllBcns(
            @RequestParam(required = false) String numeroBcn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateBcn,
            @RequestParam(required = false) String codeDistrict) {

        // Implémentez la logique de filtrage ici
        return bcnService.getBcnsByCriteria(numeroBcn, dateBcn, codeDistrict);
    }

    // Récupérer un BCN par ID
    @GetMapping("/{id}")
    public Bcn getBcnById(@PathVariable Long id) {
        return bcnService.getBcnById(id);
    }

    // Mettre à jour un BCN
    @PutMapping("/{id}")
    public Bcn updateBcn(@PathVariable Long id, @RequestBody Bcn bcn) {
        return bcnService.updateBcn(id, bcn);
    }

    // Supprimer un BCN
    @DeleteMapping("/{id}")
    public void deleteBcn(@PathVariable Long id) {
        bcnService.deleteBcn(id);
    }

    // Créer un BCN
    @PostMapping
    public ResponseEntity<Bcn> createBcn(@RequestBody Bcn bcn) {
        try {
            Bcn savedBcn = bcnService.saveBcn(bcn);
            return new ResponseEntity<>(savedBcn, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log l'erreur
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}