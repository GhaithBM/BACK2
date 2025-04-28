package com.example.PFE1.service;

import com.example.PFE1.model.LigneBcn;

import java.util.List;

public interface ILigneBcnService {

    LigneBcn saveLigneBcn(LigneBcn ligneBcn);

    List<LigneBcn> getAllLigneBcns();

    LigneBcn getLigneBcnById(Long id);

    LigneBcn updateLigneBcn(Long id, LigneBcn ligneBcnDetails);

    void deleteLigneBcn(Long id);
}