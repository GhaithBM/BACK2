package com.example.PFE1.service;

import com.example.PFE1.model.Ravitaillement;

import java.util.Date;
import java.util.List;

public interface IRavitaillementService {


    Ravitaillement getRavitaillementById(Long id);

    void deleteRavitaillement(Long id);

    List<Ravitaillement> getRavitaillementsByCriteria(Integer matricule, String nom, String fonction, String affectation, String district, Date date, String sequence);

    Ravitaillement saveRavitaillement(Ravitaillement ravitaillement);

    Ravitaillement updateRavitaillement(Long id, Ravitaillement ravitaillementDetails);

    List<Integer> getAllMatricules();

    List<String> getAllCodesDistrict();
}
