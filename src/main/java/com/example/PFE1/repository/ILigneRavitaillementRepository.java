package com.example.PFE1.repository;

import com.example.PFE1.model.LigneRavitaillement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILigneRavitaillementRepository extends JpaRepository<LigneRavitaillement, Long> {
}
