package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//@Table(name = "type_titres")
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(name = "idx_code_type_titre", columnList = "CodeTypeTitre")})
public class TypeTitre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codeTypeTitre;

    private String libelleTypeTitre;

    @OneToMany(mappedBy = "typeTitre")
    @JsonManagedReference("titre-typetitre")
    private List<Titre> titres;


    public List<Titre> getTitres() {
        return titres;
    }

    public void setTitres(List<Titre> titres) {
        this.titres = titres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeTitre() {
        return codeTypeTitre;
    }

    public void setCodeTypeTitre(String codeTypeTitre) {
        this.codeTypeTitre = codeTypeTitre;
    }

    public String getLibelleTypeTitre() {
        return libelleTypeTitre;
    }

    public void setLibelleTypeTitre(String libelleTypeTitre) {
        this.libelleTypeTitre = libelleTypeTitre;
    }
}