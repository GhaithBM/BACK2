package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.FeuilleDeRoute;
import com.example.PFE1.model.LigneFeuille;
import com.example.PFE1.repository.IFeuilleDeRouteRepository;
import com.example.PFE1.repository.ILigneFeuilleRepository;
import com.example.PFE1.service.IFeuilleDeRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class FeuilleDeRouteService implements IFeuilleDeRouteService {

//    private static final Logger logger = LoggerFactory.getLogger(FeuilleDeRouteService.class);

    @Autowired
    private IFeuilleDeRouteRepository repository;

    @Autowired
    private ILigneFeuilleRepository ligneRepository;

    public List<FeuilleDeRoute> getAllFeuillesDeRoute() {
        return repository.findAll();
    }

    public FeuilleDeRoute getFeuilleDeRouteById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteFeuilleDeRoute(Long id) {
        repository.deleteById(id);
    }

    public List<FeuilleDeRoute> getFeuillesByCriteria(String typeVente, Integer matricule, String district, Date dateVersement, Date dateVente) {
        return repository.findByCriteria(typeVente, matricule, district, dateVersement, dateVente);
    }

//    public FeuilleDeRoute saveFeuilleDeRoute(FeuilleDeRoute feuille) {
//        if (feuille.getLignes() != null) {
//            for (LigneFeuille ligne : feuille.getLignes()) {
//                ligne.setFeuilleDeRoute(feuille);
//                logger.debug("LigneFeuille set for FeuilleDeRoute with ID: {}", feuille.getId());
//            }
//        } else {
//            logger.warn("FeuilleDeRoute has no LigneFeuille entities.");
//        }
//        FeuilleDeRoute savedFeuille = repository.save(feuille);
//        logger.info("FeuilleDeRoute saved with ID: {}", savedFeuille.getId());
//        return savedFeuille;
//    }

    @Transactional
    public FeuilleDeRoute saveFeuilleDeRoute(FeuilleDeRoute feuille) {
        if (feuille.getLignes() != null) {
            for (LigneFeuille ligne : feuille.getLignes()) {
                ligne.setFeuilleDeRoute(feuille); // Associer chaque ligne à la feuille
            }
        }
        return repository.save(feuille);
    }

    @Transactional
    public FeuilleDeRoute updateFeuilleDeRoute(Long id, FeuilleDeRoute feuilleDetails) {
        FeuilleDeRoute feuille = repository.findById(id).orElseThrow(() -> new RuntimeException("Feuille not found"));

        // Mettre à jour les champs de la feuille
        feuille.setTypeVente(feuilleDetails.getTypeVente());
        feuille.setDistrict(feuilleDetails.getDistrict());
        feuille.setCaissier(feuilleDetails.getCaissier());
        feuille.setDateVersement(feuilleDetails.getDateVersement());
        feuille.setDateVente(feuilleDetails.getDateVente());
        feuille.setMatricule(feuilleDetails.getMatricule());
        feuille.setNom(feuilleDetails.getNom());
        feuille.setPrenom(feuilleDetails.getPrenom());
        feuille.setFonction(feuilleDetails.getFonction());
        feuille.setTypeTitre(feuilleDetails.getTypeTitre());
        feuille.setTour(feuilleDetails.getTour());
        feuille.setGroupe(feuilleDetails.getGroupe());
        feuille.setSequenceVente(feuilleDetails.getSequenceVente());

        // Mettre à jour les lignes
        List<LigneFeuille> updatedLignes = feuilleDetails.getLignes();
        if (updatedLignes != null) {
            // Supprimer les lignes non présentes dans la nouvelle liste
            feuille.getLignes().removeIf(ligne -> !updatedLignes.contains(ligne));

            // Ajouter ou mettre à jour les nouvelles lignes
            for (LigneFeuille ligne : updatedLignes) {
                ligne.setFeuilleDeRoute(feuille);
                if (!feuille.getLignes().contains(ligne)) {
                    feuille.getLignes().add(ligne);
                }
            }
        } else {
            feuille.setLignes(new ArrayList<>()); // Assurez-vous que la liste n'est pas null
        }

        return repository.save(feuille);
    }
}
