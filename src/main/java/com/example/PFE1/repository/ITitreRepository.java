package com.example.PFE1.repository;

import com.example.PFE1.model.Titre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ITitreRepository extends JpaRepository<Titre, Long> , JpaSpecificationExecutor<Titre> {
    // Ici, tu peux ajouter des méthodes personnalisées si nécessaire.
}