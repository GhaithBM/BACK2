package com.example.PFE1.repository;

import com.example.PFE1.model.Ligne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ILigneRepository extends JpaRepository<Ligne, Long> , JpaSpecificationExecutor<Ligne> {
}