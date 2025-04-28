package com.example.PFE1.controller;


import com.example.PFE1.model.Tour;
import com.example.PFE1.service.ITourService;
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
@RequestMapping("/api/tours")
public class TourController {

    @Autowired
    private ITourService tourService;

    // Créer un tour
    @PostMapping("/createTour")
    // http://localhost:8080/api/tours/createTour
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour savedTour = tourService.createTour(tour);
        return new ResponseEntity<>(savedTour, HttpStatus.CREATED);
    }

    // Récupérer un tour par son code (ID)
    @GetMapping("/{id}")
    // http://localhost:8080/api/tours/{codeTour}
    public ResponseEntity<Tour> getTourById(@PathVariable("id") Long id) {
        Tour tour = tourService.getTourById(id);
        if (tour != null) {
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer tous les tours
    @GetMapping("/getAllTours")
    // http://localhost:8080/api/tours/getAllTours
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tourList = tourService.getAllTours();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    // Mettre à jour un tour
    @PutMapping("/{id}")
    // http://localhost:8080/api/tours/{codeTour}
    public ResponseEntity<Tour> updateTour(@PathVariable("id") Long id, @RequestBody Tour tour) {
        tour.setCodeTour(id);  // Assurez-vous que le codeTour est bien mis à jour dans l'objet Tour
        Tour updatedTour = tourService.updateTour(tour);
        if (updatedTour != null) {
            return new ResponseEntity<>(updatedTour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un tour

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable Long id) {
        try {
            tourService.deleteTour(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "District supprimé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @PostMapping("/Search")
    // http://localhost:8080/api/tours/getAllTours
    public ResponseEntity<List<Tour>> Search(@RequestBody Tour tour) {
        List<Tour> tourList = tourService.SearchFilter(tour);
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }
}