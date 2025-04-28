package com.example.PFE1.service;

import com.example.PFE1.model.Fonction;

import java.util.List;

public interface IFonctionService {
    Fonction createFonction(Fonction fonction);
    Fonction getFonctionById(Long id);
    List<Fonction> getAllFonctions();
//    List<Fonction> SearchFilter(Fonction fonction);
    Fonction updateFonction(Fonction fonction);
    void deleteFonction(Long id);
    List<Fonction> searchFonctions(Fonction fonction);
}