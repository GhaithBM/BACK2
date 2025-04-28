package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Bcn {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroBcn;
    private Date dateBcn;

    @ManyToOne
    @JoinColumn(name = "CodeDistrict", referencedColumnName = "CodeDistrict", insertable = false, updatable = false)
    @JsonBackReference("bcn-district")
    private District district;

    private String codeDistrict;

    @OneToMany(mappedBy = "bcn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LigneBcn> lignes = new ArrayList<>();

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

    public String getNumeroBcn() {
        return numeroBcn;
    }

    public void setNumeroBcn(String numeroBcn) {
        this.numeroBcn = numeroBcn;
    }

    public Date getDateBcn() {
        return dateBcn;
    }

    public void setDateBcn(Date dateBcn) {
        this.dateBcn = dateBcn;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public List<LigneBcn> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneBcn> lignes) {
        this.lignes = lignes;
    }



}
