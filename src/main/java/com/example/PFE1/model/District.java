package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(indexes = {@Index(name = "idx_code_district", columnList = "CodeDistrict")})
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codeDistrict;

    private String libelleDistrict;
    private String libelleDistrictAR;
    private String reseaux;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("caissier-district")
    private List<Caissier> caissiers;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("agent-district")
    private List<Agent> agents;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("ligne-district")
    private List<Ligne> lignes;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("groupe-district")
    private List<Groupe> groupes;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("tour-district")
    private List<Tour> tours;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("bcn-district")
    private List<Bcn> bcns;

    @OneToMany(mappedBy = "district")
    @JsonManagedReference("rvt-district")
    private List<Ravitaillement> rvts;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ravitaillement> getRvts() {
        return rvts;
    }

    public void setRvts(List<Ravitaillement> rvts) {
        this.rvts = rvts;
    }

    public List<Bcn> getBcns() {
        return bcns;
    }

    public void setBcns(List<Bcn> bcns) {
        this.bcns = bcns;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public List<Caissier> getCaissiers() {
        return caissiers;
    }

    public void setCaissiers(List<Caissier> caissiers) {
        this.caissiers = caissiers;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public String getLibelleDistrict() {
        return libelleDistrict;
    }

    public void setLibelleDistrict(String libelleDistrict) {
        this.libelleDistrict = libelleDistrict;
    }

    public String getLibelleDistrictAR() {
        return libelleDistrictAR;
    }

    public void setLibelleDistrictAR(String libelleDistrictAR) {
        this.libelleDistrictAR = libelleDistrictAR;
    }

    public String getReseaux() {
        return reseaux;
    }

    public void setReseaux(String reseaux) {
        this.reseaux = reseaux;
    }
}
