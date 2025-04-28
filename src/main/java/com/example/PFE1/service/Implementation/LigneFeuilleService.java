package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.LigneFeuille;
import com.example.PFE1.repository.ILigneFeuilleRepository;
import com.example.PFE1.service.ILigneFeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneFeuilleService implements ILigneFeuilleService {

    @Autowired
    private ILigneFeuilleRepository repository;

    public LigneFeuille saveLigneFeuille(LigneFeuille ligneFeuille) {
        return repository.save(ligneFeuille);
    }

    public List<LigneFeuille> getAllLigneFeuilles() {
        return repository.findAll();
    }

    public LigneFeuille getLigneFeuilleById(Long id) {
        Optional<LigneFeuille> ligneFeuille = repository.findById(id);
        return ligneFeuille.orElse(null);
    }

    public LigneFeuille updateLigneFeuille(Long id, LigneFeuille ligneFeuilleDetails) {
        LigneFeuille ligneFeuille = getLigneFeuilleById(id);
        if (ligneFeuille != null) {
            ligneFeuille.setAbr(ligneFeuilleDetails.getAbr());
            ligneFeuille.setDebut(ligneFeuilleDetails.getDebut());
            ligneFeuille.setFin(ligneFeuilleDetails.getFin());
            ligneFeuille.setMontant(ligneFeuilleDetails.getMontant());
            ligneFeuille.setPrix(ligneFeuilleDetails.getPrix());
            ligneFeuille.setQte(ligneFeuilleDetails.getQte());
            ligneFeuille.setSerie(ligneFeuilleDetails.getSerie());
            ligneFeuille.setTitre(ligneFeuilleDetails.getTitre());
            return repository.save(ligneFeuille);
        }
        return null;
    }

    public void deleteLigneFeuille(Long id) {
        repository.deleteById(id);
    }
}
