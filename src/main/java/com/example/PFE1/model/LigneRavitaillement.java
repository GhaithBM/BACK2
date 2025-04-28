package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneRavitaillement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String libelleTitre;
    private String serie;
    private int numeroDebut;
    private int numeroFin;
    private int quantite;
    private String be;
    private String dernierRavitaillement;

    @ManyToOne
    @JoinColumn(name = "ravitaillement_id")
    @JsonBackReference
    private Ravitaillement ravitaillement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLibelleTitre() {
        return libelleTitre;
    }

    public void setLibelleTitre(String libelleTitre) {
        this.libelleTitre = libelleTitre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumeroDebut() {
        return numeroDebut;
    }

    public void setNumeroDebut(int numeroDebut) {
        this.numeroDebut = numeroDebut;
    }

    public int getNumeroFin() {
        return numeroFin;
    }

    public void setNumeroFin(int numeroFin) {
        this.numeroFin = numeroFin;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getBe() {
        return be;
    }

    public void setBe(String be) {
        this.be = be;
    }

    public String getDernierRavitaillement() {
        return dernierRavitaillement;
    }

    public void setDernierRavitaillement(String dernierRavitaillement) {
        this.dernierRavitaillement = dernierRavitaillement;
    }

    public Ravitaillement getRavitaillement() {
        return ravitaillement;
    }

    public void setRavitaillement(Ravitaillement ravitaillement) {
        this.ravitaillement = ravitaillement;
    }
}
