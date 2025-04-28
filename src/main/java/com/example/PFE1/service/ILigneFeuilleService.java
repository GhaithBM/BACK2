package com.example.PFE1.service;

import com.example.PFE1.model.LigneFeuille;

import java.util.List;

public interface ILigneFeuilleService {

    LigneFeuille saveLigneFeuille(LigneFeuille ligneFeuille);

    List<LigneFeuille> getAllLigneFeuilles();

    LigneFeuille getLigneFeuilleById(Long id);

    LigneFeuille updateLigneFeuille(Long id, LigneFeuille ligneFeuilleDetails);

    void deleteLigneFeuille(Long id);
}
