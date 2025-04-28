package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeuilleDeRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeVente;
    private String district;
    private int caissier;
    private Date dateVersement;
    private Date dateVente;
    private String nom;
    private String prenom;
    private String fonction;
    private String typeTitre;
    private int tour;
    private int groupe;
    private int sequenceVente;

    @ManyToOne
    @JoinColumn(name = "matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    @JsonBackReference("FdeR-agent")
    private Agent agent;

    private Integer matricule;

    @OneToMany(mappedBy = "feuilleDeRoute", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LigneFeuille> lignes = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public List<LigneFeuille> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFeuille> lignes) {
        this.lignes = lignes;
    }

    public String getTypeVente() {
        return typeVente;
    }

    public void setTypeVente(String typeVente) {
        this.typeVente = typeVente;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCaissier() {
        return caissier;
    }

    public void setCaissier(int caissier) {
        this.caissier = caissier;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public Integer getMatricule() {
        return matricule;
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

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(String typeTitre) {
        this.typeTitre = typeTitre;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public int getSequenceVente() {
        return sequenceVente;
    }

    public void setSequenceVente(int sequenceVente) {
        this.sequenceVente = sequenceVente;
    }

}
