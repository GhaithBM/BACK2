package com.example.PFE1.repository;

import com.example.PFE1.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITourRepository extends JpaRepository<Tour, Long> ,  JpaSpecificationExecutor<Tour> {
    // Ici, nous pouvons ajouter des méthodes personnalisées si nécessaire
    // Par exemple, pour chercher une tournée par CodeTour :
    Optional<Tour> findByCodeTour(Long codeTour);
}