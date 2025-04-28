package com.example.PFE1.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//@Table(name = "agent")
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique (Matricule de l'agent)

    @ManyToOne
    @JoinColumn(name = "CodeDistrict", referencedColumnName = "CodeDistrict", insertable = false, updatable = false)
    @JsonBackReference("agent-district")
    private District district;

    private String codeDistrict;  // Code du district de l'agent

    @ManyToOne
    @JoinColumn(name = "CodeFonction", referencedColumnName = "CodeFonction", insertable = false, updatable = false)
    @JsonBackReference("agent-fonction")
    private Fonction fonction;

    private String codeFonction;  // Code de la fonction de l'agent


    @OneToMany(mappedBy = "agent")
    @JsonManagedReference("rvt-agent")
    private List<Ravitaillement> rvts;

    @OneToMany(mappedBy = "agent")
    @JsonManagedReference("FdeR-agent")
    private List<FeuilleDeRoute> feuillesDeRoute;


    @Column(unique = true , name = "matricule")
    private int matricule ;

    private String nom;  // Nom de l'agent

    private String prenom;  // Prénom de l'agent

    private String etatAgent;  // Code de l'état de l'agent (ex: actif, inactif)

    private Integer numTampon;  // Numéro de tampon de l'agent

    private String bureauAffectation;  // Bureau d'affectation de l'agent

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FeuilleDeRoute> getFeuillesDeRoute() {
        return feuillesDeRoute;
    }

    public void setFeuillesDeRoute(List<FeuilleDeRoute> feuillesDeRoute) {
        this.feuillesDeRoute = feuillesDeRoute;
    }

    public List<Ravitaillement> getRvts() {
        return rvts;
    }

    public void setRvts(List<Ravitaillement> rvts) {
        this.rvts = rvts;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtatAgent() {
        return etatAgent;
    }

    public void setEtatAgent(String etatAgent) {
        this.etatAgent = etatAgent;
    }

    public Integer getNumTampon() {
        return numTampon;
    }

    public void setNumTampon(Integer numTampon) {
        this.numTampon = numTampon;
    }

    public String getBureauAffectation() {
        return bureauAffectation;
    }

    public void setBureauAffectation(String bureauAffectation) {
        this.bureauAffectation = bureauAffectation;
    }
}
