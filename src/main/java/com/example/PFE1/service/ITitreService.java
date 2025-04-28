package com.example.PFE1.service;

import com.example.PFE1.model.Titre;

import java.util.List;
import java.util.Optional;

public interface ITitreService {
    Titre createTitre(Titre titre);
    Titre getTitreById(Long id);
    List<Titre> getAllTitres();
    List<Titre> SearchFilter(Titre titre);
    Titre updateTitre(Titre titre);
    void deleteTitre(Long id);
    Optional<Titre> findById(Long titreId);
}
