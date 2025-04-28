package com.example.PFE1.repository;

import com.example.PFE1.model.LigneFeuille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILigneFeuilleRepository extends JpaRepository<LigneFeuille, Long> {
}
