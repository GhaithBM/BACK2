package com.example.PFE1.repository;

import com.example.PFE1.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface IGroupeRepository extends JpaRepository<Groupe, Long> ,JpaSpecificationExecutor<Groupe> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
