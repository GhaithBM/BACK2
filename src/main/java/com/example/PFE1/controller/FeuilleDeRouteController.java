package com.example.PFE1.controller;

import com.example.PFE1.model.FeuilleDeRoute;
import com.example.PFE1.service.IFeuilleDeRouteService;
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
@RequestMapping("/api/feuilles")
@CrossOrigin(origins = "http://localhost:4200")
public class FeuilleDeRouteController {

    @Autowired
    private IFeuilleDeRouteService service;

    // Récupérer toutes les feuilles de route
    @GetMapping
    public List<FeuilleDeRoute> getAllFeuillesDeRoute(
            @RequestParam(required = false) String typeVente,
            @RequestParam(required = false) Integer matricule,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateVersement,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateVente) {

        // Implémentez la logique de filtrage ici
        return service.getFeuillesByCriteria(typeVente, matricule, district, dateVersement, dateVente);
    }

    // Récupérer une feuille de route par ID
    @GetMapping("/{id}")
    public FeuilleDeRoute getFeuilleDeRouteById(@PathVariable Long id) {
        return service.getFeuilleDeRouteById(id);
    }

    // Mettre à jour une feuille de route

//    @PutMapping("/{id}")
//    public FeuilleDeRoute updateFeuilleDeRoute(@PathVariable Long id, @RequestBody FeuilleDeRoute feuille) {
//        feuille.setId(id);
//        return service.saveFeuilleDeRoute(feuille);
//    }

    @PutMapping("/{id}")
    public FeuilleDeRoute updateFeuilleDeRoute(@PathVariable Long id, @RequestBody FeuilleDeRoute feuille) {
        return service.updateFeuilleDeRoute(id, feuille);
    }


    // Supprimer une feuille de route
    @DeleteMapping("/{id}")
    public void deleteFeuilleDeRoute(@PathVariable Long id) {
        service.deleteFeuilleDeRoute(id);
    }

    @PostMapping
    public ResponseEntity<FeuilleDeRoute> createFeuilleDeRoute(@RequestBody FeuilleDeRoute feuille) {
        try {
            FeuilleDeRoute savedFeuille = service.saveFeuilleDeRoute(feuille);
            return new ResponseEntity<>(savedFeuille, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log l'erreur
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


