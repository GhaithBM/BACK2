package com.example.PFE1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.*;

@Entity
//@Table(name = "tours")
@NoArgsConstructor
@AllArgsConstructor

public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codeTour;

    @ManyToOne
    @JoinColumn(name = "codeDistrict", referencedColumnName = "CodeDistrict", insertable = false, updatable = false)
    @JsonBackReference("tour-district")
    private District district;

    private String codeDistrict;

    private String libelleTour;

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

    public Long getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(Long codeTour) {
        this.codeTour = codeTour;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public String getLibelleTour() {
        return libelleTour;
    }

    public void setLibelleTour(String libelleTour) {
        this.libelleTour = libelleTour;
    }
}
