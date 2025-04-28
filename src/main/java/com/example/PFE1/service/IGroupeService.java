package com.example.PFE1.service;

import com.example.PFE1.model.Groupe;

import java.util.List;


public interface IGroupeService {
    Groupe createGroupe(Groupe groupe);
    Groupe getGroupeById(Long id);
    List<Groupe> getAllGroupes();
    List<Groupe> SearchFilter(Groupe groupe);
    Groupe updateGroupe(Groupe groupe);
    void deleteGroupe(Long id);
}
