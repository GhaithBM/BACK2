package com.example.PFE1.service;

import com.example.PFE1.model.Caissier;

import java.util.List;

public interface ICaissierService {
    Caissier createCaissier(Caissier caissier);
    Caissier getCaissierById(Long id);
    List<Caissier> getAllCaissiers();
    List<Caissier> SearchFilter(Caissier caissier);
    Caissier updateCaissier(Caissier caissier);
    void deleteCaissier(Long id);
}
