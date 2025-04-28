package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.LigneRavitaillement;
import com.example.PFE1.repository.ILigneRavitaillementRepository;
import com.example.PFE1.service.ILigneRavitaillementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneRavitaillementService implements ILigneRavitaillementService {

    @Autowired
    private ILigneRavitaillementRepository repository;

    public LigneRavitaillement saveLigneRavitaillement(LigneRavitaillement ligneRavitaillement) {
        return repository.save(ligneRavitaillement);
    }

    public List<LigneRavitaillement> getAllLigneRavitaillements() {
        return repository.findAll();
    }

    public LigneRavitaillement getLigneRavitaillementById(Long id) {
        Optional<LigneRavitaillement> ligneRavitaillement = repository.findById(id);
        return ligneRavitaillement.orElse(null);
    }

    public LigneRavitaillement updateLigneRavitaillement(Long id, LigneRavitaillement ligneRavitaillementDetails) {
        LigneRavitaillement ligneRavitaillement = getLigneRavitaillementById(id);
        if (ligneRavitaillement != null) {
            ligneRavitaillement.setTitre(ligneRavitaillementDetails.getTitre());
            ligneRavitaillement.setLibelleTitre(ligneRavitaillementDetails.getLibelleTitre());
            ligneRavitaillement.setSerie(ligneRavitaillementDetails.getSerie());
            ligneRavitaillement.setNumeroDebut(ligneRavitaillementDetails.getNumeroDebut());
            ligneRavitaillement.setNumeroFin(ligneRavitaillementDetails.getNumeroFin());
            ligneRavitaillement.setQuantite(ligneRavitaillementDetails.getQuantite());
            ligneRavitaillement.setBe(ligneRavitaillementDetails.getBe());
            ligneRavitaillement.setDernierRavitaillement(ligneRavitaillementDetails.getDernierRavitaillement());
            return repository.save(ligneRavitaillement);
        }
        return null;
    }

    public void deleteLigneRavitaillement(Long id) {
        repository.deleteById(id);
    }
}
