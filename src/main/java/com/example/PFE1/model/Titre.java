package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;



@Entity
//@Table(name = "titres")
@NoArgsConstructor
@AllArgsConstructor
public class Titre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CodeTypeTitre", referencedColumnName = "CodeTypeTitre", insertable = false, updatable = false)
    @JsonBackReference("titre-typetitre")
    private TypeTitre typeTitre;

    private String codeTypeTitre;
    private String libelleTitre;
    private Integer stkAlert;

    private Integer nombrePoint;
    private String uniteRVT;
    private String abr;
    private Boolean actif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTitre getTypeTitre() {
        return typeTitre;
    }

    public void setTypeTitre(TypeTitre typeTitre) {
        this.typeTitre = typeTitre;
    }

    public String getCodeTypeTitre() {
        return codeTypeTitre;
    }

    public void setCodeTypeTitre(String codeTypeTitre) {
        this.codeTypeTitre = codeTypeTitre;
    }

    public String getLibelleTitre() {
        return libelleTitre;
    }

    public void setLibelleTitre(String libelleTitre) {
        this.libelleTitre = libelleTitre;
    }

    public Integer getNombrePoint() {
        return nombrePoint;
    }

    public void setNombrePoint(Integer nombrePoint) {
        this.nombrePoint = nombrePoint;
    }

    public String getUniteRVT() {
        return uniteRVT;
    }

    public void setUniteRVT(String uniteRVT) {
        this.uniteRVT = uniteRVT;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public Integer getStkAlert() {
        return stkAlert;
    }

    public void setStkAlert(Integer stkAlert) {
        this.stkAlert = stkAlert;
    }



    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}
