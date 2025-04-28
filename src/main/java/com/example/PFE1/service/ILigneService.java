package com.example.PFE1.service;

import com.example.PFE1.model.Ligne;

import java.util.List;

public interface ILigneService {
    Ligne createLigne(Ligne ligne);
    Ligne getLigneById(Long id);
    List<Ligne> getAllLignes();
    List<Ligne> SearchFilter(Ligne ligne);
    Ligne updateLigne(Ligne ligne);
    void deleteLigne(Long id);
}