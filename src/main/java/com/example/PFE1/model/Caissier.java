package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "caissier")
@NoArgsConstructor
@AllArgsConstructor
public class Caissier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CodeDistrict", referencedColumnName = "CodeDistrict", insertable = false, updatable = false)
    @JsonBackReference("caissier-district")
    private District district;

    private String codeDistrict;

    private String codeCaissier;

//    @JsonProperty("mle")//correspond au nom attendu par le front
    @Column(unique = true)
    private String mle;

//    @JsonProperty("libelleCaissier")
    private String libelleCaissier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getMle() {
        return mle;
    }

    public void setMle(String mle) {
        this.mle = mle;
    }

    public String getCodeCaissier() {
        return codeCaissier;
    }

    public void setCodeCaissier(String codeCaissier) {
        this.codeCaissier = codeCaissier;
    }

    public String getLibelleCaissier() {
        return libelleCaissier;
    }

    public void setLibelleCaissier(String libelleCaissier) {
        this.libelleCaissier = libelleCaissier;
    }
}
