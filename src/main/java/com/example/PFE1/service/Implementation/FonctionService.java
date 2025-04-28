package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.Fonction;
import com.example.PFE1.repository.IFonctionRepository;
import com.example.PFE1.service.IFonctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

@Service
public class FonctionService implements IFonctionService {

    @Autowired
    private IFonctionRepository fonctionRepository;

    @Override
    public Fonction createFonction(Fonction fonction) {
        // Vérification d'un doublon, ou toute autre logique avant la création
        return fonctionRepository.save(fonction);
    }

    @Override
    public Fonction getFonctionById(Long id) {
        Optional<Fonction> optionalFonction = fonctionRepository.findById(id);
        return optionalFonction.orElseThrow(() -> new RuntimeException("Fonction not found with id: " + id));
    }

    @Override
    public List<Fonction> getAllFonctions() {
        return fonctionRepository.findAll();
    }

//    @Override
//    public List<Fonction> SearchFilter(Fonction fonction) {
//        Specification<Fonction> spec = new Specification<Fonction>() {
//            @Override
//            public Predicate toPredicate(Root<Fonction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate predicate = criteriaBuilder.conjunction();  // Condition vide
//
//                // Vérification et ajout des conditions de filtre pour CodeFonction
//                if (fonction.getCodeFonction() != null && !fonction.getCodeFonction().isEmpty()) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("CodeFonction"), fonction.getCodeFonction()));
//                }
//
//                // Vérification et ajout des conditions de filtre pour LibelleFonction
//                if (fonction.getLibelleFonction() != null && !fonction.getLibelleFonction().isEmpty()) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("libelleFonction"), fonction.getLibelleFonction()));
//                }
//
//                return predicate;
//            }
//        };
//
//        return fonctionRepository.findAll(spec);
//   }


    @Override
    public Fonction updateFonction(Fonction fonction) {
        // Vérification de l'existence avant la mise à jour
        Fonction existingFonction = fonctionRepository.findById(fonction.getId())
                .orElseThrow(() -> new RuntimeException("Fonction not found with id: " + fonction.getId()));

        existingFonction.setCodeFonction(fonction.getCodeFonction());
        existingFonction.setLibelleFonction(fonction.getLibelleFonction());

        return fonctionRepository.save(existingFonction);
    }

    @Override
    public void deleteFonction(Long id) {
        if (!fonctionRepository.existsById(id)) {
            throw new RuntimeException("Fonction not found with id: " + id);
        }
        fonctionRepository.deleteById(id);
    }

    @Override
    public List<Fonction> searchFonctions(Fonction fonction) {
        return fonctionRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (fonction.getCodeFonction() != null && !fonction.getCodeFonction().isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("codeFonction")), "%" + fonction.getCodeFonction().toLowerCase() + "%"));
            }

            if (fonction.getLibelleFonction() != null && !fonction.getLibelleFonction().isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("libelleFonction")), "%" + fonction.getLibelleFonction().toLowerCase() + "%"));
            }

            return predicate;
        });
    }

}
