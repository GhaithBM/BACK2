package com.example.PFE1.service;

import com.example.PFE1.model.LigneRavitaillement;

import java.util.List;

public interface ILigneRavitaillementService {

    LigneRavitaillement saveLigneRavitaillement(LigneRavitaillement ligneRavitaillement);

    List<LigneRavitaillement> getAllLigneRavitaillements();

    LigneRavitaillement getLigneRavitaillementById(Long id);

    LigneRavitaillement updateLigneRavitaillement(Long id, LigneRavitaillement ligneRavitaillementDetails);

    void deleteLigneRavitaillement(Long id);
}
