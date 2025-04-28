package com.example.PFE1.service;


import com.example.PFE1.model.TypeTitre;

import java.util.List;

public interface ITypeTitreService {
    TypeTitre createTypeTitre(TypeTitre typeTitre);
    TypeTitre getTypeTitreById(Long typeTitreId);
    List<TypeTitre> getAllTypeTitres();
    List<TypeTitre> SearchFilter(TypeTitre typeTitre);
    TypeTitre updateTypeTitre(TypeTitre typeTitre);
    void deleteTypeTitre(Long typeTitreId);
}