package com.example.PFE1.service;

import com.example.PFE1.model.Agent;

import java.util.List;

public interface IAgentService {
    Agent createAgent(Agent agent);
    Agent getAgentById(Long id);
    List<Agent> getAllAgents();
//    List<Agent> SearchFilter(Agent agent);
    Agent updateAgent(Agent agent);
    void deleteAgent(Long id);
    List<Agent> rechercherAgents(Integer matricule, String codeDistrict, String codeFonction, String nom,
                                        String prenom, String etat, String numtampon, String bureauAffectation,
                                        String libelleFonction);
    List<Agent> searchAgents(Integer matricule, String codeDistrict, String codeFonction,
                                    String nom, String prenom, String etatAgent,
                                    Integer numTampon, String bureauAffectation);

}
