package com.example.PFE1.service;

import com.example.PFE1.model.Bcn;

import java.util.Date;
import java.util.List;

public interface IBcnService {

    List<Bcn> getAllBcns();

    Bcn getBcnById(Long id);

    void deleteBcn(Long id);

    List<Bcn> getBcnsByCriteria(String numeroBcn, Date dateBcn, String codeDistrict);

    Bcn saveBcn(Bcn bcn);

    Bcn updateBcn(Long id, Bcn bcnDetails);
}
