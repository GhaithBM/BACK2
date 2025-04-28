package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.LigneRavitaillement;
import com.example.PFE1.model.Ravitaillement;
import com.example.PFE1.repository.ILigneRavitaillementRepository;
import com.example.PFE1.repository.IRavitaillementRepository;
import com.example.PFE1.service.IRavitaillementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RavitaillementService implements IRavitaillementService {

    private static final Logger logger = LoggerFactory.getLogger(RavitaillementService.class);

    @Autowired
    private IRavitaillementRepository ravitaillementRepository;

    @Autowired
    private ILigneRavitaillementRepository ligneRavitaillementRepository;

    public List<Ravitaillement> getAllRavitaillements() {
        return ravitaillementRepository.findAll();
    }

    public Ravitaillement getRavitaillementById(Long id) {
        return ravitaillementRepository.findById(id).orElse(null);
    }

    public void deleteRavitaillement(Long id) {
        ravitaillementRepository.deleteById(id);
    }

    public List<Ravitaillement> getRavitaillementsByCriteria(Integer matricule, String nom, String fonction, String affectation, String codeDistrict, Date date, String sequence) {
        return ravitaillementRepository.findByCriteria(matricule, nom, fonction, affectation, codeDistrict, date, sequence);
    }

    public Ravitaillement saveRavitaillement(Ravitaillement ravitaillement) {
        if (ravitaillement.getLignes() != null) {
            for (LigneRavitaillement ligne : ravitaillement.getLignes()) {
                ligne.setRavitaillement(ravitaillement); // Associer chaque ligne au Ravitaillement
            }
        }
        return ravitaillementRepository.save(ravitaillement);
    }

    public Ravitaillement updateRavitaillement(Long id, Ravitaillement ravitaillementDetails) {
        Ravitaillement ravitaillement = ravitaillementRepository.findById(id).orElseThrow(() -> new RuntimeException("Ravitaillement not found"));

        // Mettre à jour les champs du Ravitaillement
        ravitaillement.setMatricule(ravitaillementDetails.getMatricule());
        ravitaillement.setNom(ravitaillementDetails.getNom());
        ravitaillement.setFonction(ravitaillementDetails.getFonction());
        ravitaillement.setAffectation(ravitaillementDetails.getAffectation());
        ravitaillement.setCodeDistrict(ravitaillementDetails.getCodeDistrict());
        ravitaillement.setDate(ravitaillementDetails.getDate());
        ravitaillement.setSequence(ravitaillementDetails.getSequence());

        // Mettre à jour les lignes
        List<LigneRavitaillement> updatedLignes = ravitaillementDetails.getLignes();
        if (updatedLignes != null) {
            // Supprimer les lignes non présentes dans la nouvelle liste
            ravitaillement.getLignes().removeIf(ligne -> !updatedLignes.contains(ligne));

            // Ajouter ou mettre à jour les nouvelles lignes
            for (LigneRavitaillement ligne : updatedLignes) {
                ligne.setRavitaillement(ravitaillement);
                if (!ravitaillement.getLignes().contains(ligne)) {
                    ravitaillement.getLignes().add(ligne);
                }
            }
        } else {
            ravitaillement.setLignes(List.of()); // Assurez-vous que la liste n'est pas null
        }

        return ravitaillementRepository.save(ravitaillement);
    }

    public List<Integer> getAllMatricules() {
        return ravitaillementRepository.findAllMatricules();
    }

    public List<String> getAllCodesDistrict() {
        return ravitaillementRepository.findAllCodeDistricts();
    }

    public List<String> findDistrictsLike(String query) {
        return ravitaillementRepository.findDistinctCodeDistrictByCodeDistrictContainingIgnoreCase(query);
    }

    public List<String> findMatriculesLike(String query) {
        return ravitaillementRepository.findMatriculesLike(query);
    }

}
