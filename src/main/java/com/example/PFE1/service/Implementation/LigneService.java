package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.Ligne;
import com.example.PFE1.repository.ILigneRepository;
import com.example.PFE1.service.ILigneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service
public class LigneService implements ILigneService {
    @Autowired
    private ILigneRepository ligneRepository;

    @Override
    public Ligne createLigne(Ligne ligne) {
        return ligneRepository.save(ligne);
    }

    @Override
    public Ligne getLigneById(Long id) {
        return ligneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne non trouvée"));
    }

    @Override
    public List<Ligne> getAllLignes() {
        return ligneRepository.findAll();
    }

    @Override
    public List<Ligne> SearchFilter(Ligne ligne) {
        Specification<Ligne> spec = (Root<Ligne> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (ligne.getCodeDistrict() != null && !ligne.getCodeDistrict().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("codeDistrict"), ligne.getCodeDistrict()));
            }

            if (ligne.getLibelleLigne() != null && !ligne.getLibelleLigne().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("libelleLigne"), ligne.getLibelleLigne()));
            }
            if (ligne.getOrigLigne() != null && !ligne.getOrigLigne().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("origLigne"), ligne.getOrigLigne()));
            }
            if (ligne.getDesigLigne() != null && !ligne.getDesigLigne().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("desigLigne"), ligne.getDesigLigne()));
            }
            if (ligne.getReseaux() != null && !ligne.getReseaux().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("reseaux"), ligne.getReseaux()));
            }

            return predicate;
        };

        return ligneRepository.findAll(spec);
    }

    @Override
    public Ligne updateLigne(Ligne ligne) {
        Ligne existingLigne = ligneRepository.findById(ligne.getId())
                .orElseThrow(() -> new RuntimeException("Ligne non trouvée"));

        existingLigne.setCodeDistrict(ligne.getCodeDistrict());
        existingLigne.setLibelleLigne(ligne.getLibelleLigne());
        existingLigne.setOrigLigne(ligne.getOrigLigne());
        existingLigne.setDesigLigne(ligne.getDesigLigne());
        existingLigne.setReseaux(ligne.getReseaux());

        return ligneRepository.save(existingLigne);
    }

    @Override
    public void deleteLigne(Long idLigne) {
        ligneRepository.deleteById(idLigne);
    }
}