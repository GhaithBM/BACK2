package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.Titre;
import com.example.PFE1.repository.ITitreRepository;
import com.example.PFE1.service.ITitreService;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitreService implements ITitreService {

    @Autowired
    private ITitreRepository titreRepository;

    @Override
    public Titre createTitre(Titre titre) {
        return titreRepository.save(titre);
    }

    @Override
    public Titre getTitreById(Long id) {
        Optional<Titre> optionalTitre = titreRepository.findById(id);
        return optionalTitre.orElse(null);  // Retourne null si le titre n'existe pas
    }

    @Override
    public List<Titre> getAllTitres() {
        List<Titre> titreList = titreRepository.findAll();
        System.out.println("Titres récupérés : " + titreList); // Ajoutez ce log
        return titreList;
    }

    @Override
    public List<Titre> SearchFilter(Titre titre) {
        Specification<Titre> spec = new Specification<Titre>() {
            @Override
            public Predicate toPredicate(Root<Titre> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                if (titre.getCodeTypeTitre() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("CodeTypeTitre"), titre.getCodeTypeTitre()));
                }
                if (titre.getLibelleTitre() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("LibelleTitre"),  titre.getLibelleTitre() ));
                }
                if (titre.getStkAlert() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("stkAlert"), titre.getStkAlert()));
                }

                if (titre.getNombrePoint() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("NombrePoint"), titre.getNombrePoint()));
                }
                if (titre.getUniteRVT() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("UniteRVT"), titre.getUniteRVT()));
                }
                if (titre.getAbr() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("ABR"), titre.getAbr()));
                }
                if (titre.getActif() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("actif"), titre.getActif()));
                }

                return predicate;
            }
        };

        return titreRepository.findAll(spec);
    }


    @Override
    public Titre updateTitre(Titre titre) {
        Titre existingTitre = titreRepository.findById(titre.getId()).orElse(null);
        if (existingTitre != null) {
            existingTitre.setCodeTypeTitre(titre.getCodeTypeTitre());
            existingTitre.setLibelleTitre(titre.getLibelleTitre());
            existingTitre.setStkAlert(titre.getStkAlert());
            existingTitre.setNombrePoint(titre.getNombrePoint());
            existingTitre.setUniteRVT(titre.getUniteRVT());
            existingTitre.setAbr(titre.getAbr());
            existingTitre.setActif(titre.getActif());
            return titreRepository.save(existingTitre);
        }
        return null;  // Retourne null si le titre n'existe pas
    }

    @Override
    public void deleteTitre(Long id) {
        titreRepository.deleteById(id);
    }

    @Override
    public Optional<Titre> findById(Long titreId) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
}