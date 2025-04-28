package com.example.PFE1.controller;



import com.example.PFE1.model.Caissier;
import com.example.PFE1.model.District;
import com.example.PFE1.repository.ICaissierRepository;
import com.example.PFE1.service.ICaissierService;
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
@RequestMapping("/api/caissiers")
public class CaissierController {

    @Autowired
    private ICaissierService caissierService;
    @Autowired
    private ICaissierRepository caissierRepository;

    @CrossOrigin(origins = "http://localhost:4200")

    @PutMapping("/{id}")  // Correction ici
    public ResponseEntity<Caissier> modifierCaissier(@PathVariable Long id, @RequestBody Caissier caissier) {
        caissier.setId(id);
        Caissier updatedCaissier = caissierService.updateCaissier(caissier);
        return new ResponseEntity<>(updatedCaissier, HttpStatus.OK);
    }

    @PostMapping("/createCaissier")
    public ResponseEntity<Caissier> createCaissier(@RequestBody Caissier caissier) {
        Caissier savedCaissier = caissierService.createCaissier(caissier);
        return new ResponseEntity<>(savedCaissier, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caissier> getCaissierById(@PathVariable("id") Long id) {
        Caissier caissier = caissierService.getCaissierById(id);
        return new ResponseEntity<>(caissier, HttpStatus.OK);
    }

    @GetMapping("/getAllCaissiers")
    public ResponseEntity<List<Caissier>> getAllCaissiers() {
        List<Caissier> caissierList = caissierService.getAllCaissiers();
        return new ResponseEntity<>(caissierList, HttpStatus.OK);
    }



    @DeleteMapping("/deleteCaissier/{id}")
    public ResponseEntity<?> deleteCaissier(@PathVariable Long id) {
        try {
            caissierService.deleteCaissier(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Fonction supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Caissier>> searchCaissiers(
            @RequestParam(required = false) String mle,
            @RequestParam(required = false) String libelleCaissier,
            @RequestParam(required = false) String codeCaissier,
            @RequestParam(required = false) String codeDistrict
    ) {
        List<Caissier> results = caissierRepository.searchCaissiers(
                mle, libelleCaissier, codeCaissier, codeDistrict
        );
        return ResponseEntity.ok(results);
    }


}