package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;




@Entity
//@Table(name = "lignes")
@NoArgsConstructor
@AllArgsConstructor
public class Ligne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CodeDistrict", referencedColumnName = "CodeDistrict", insertable = false, updatable = false)
    @JsonBackReference("ligne-district")
    private District district;

    private String codeDistrict;
    private String libelleLigne;
    private String origLigne;
    private String desigLigne;
    private String reseaux;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLibelleLigne() {
        return libelleLigne;
    }

    public void setLibelleLigne(String libelleLigne) {
        this.libelleLigne = libelleLigne;
    }

    public String getOrigLigne() {
        return origLigne;
    }

    public void setOrigLigne(String origLigne) {
        this.origLigne = origLigne;
    }

    public String getDesigLigne() {
        return desigLigne;
    }

    public void setDesigLigne(String desigLigne) {
        this.desigLigne = desigLigne;
    }

    public String getReseaux() {
        return reseaux;
    }

    public void setReseaux(String reseaux) {
        this.reseaux = reseaux;
    }
}
