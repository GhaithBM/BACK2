package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneFeuille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String abr;
    private String titre;
    private double prix;
    private String serie;
    private int debut;
    private int fin;
    private int qte;
    private double montant;

    @ManyToOne
    @JoinColumn(name = "feuille_de_route_id")
    @JsonBackReference
    private FeuilleDeRoute feuilleDeRoute;


    public FeuilleDeRoute getFeuilleDeRoute() {
        return feuilleDeRoute;
    }

    public void setFeuilleDeRoute(FeuilleDeRoute feuilleDeRoute) {
        this.feuilleDeRoute = feuilleDeRoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
