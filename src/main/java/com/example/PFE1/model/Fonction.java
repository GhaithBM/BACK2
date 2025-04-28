package com.example.PFE1.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.*;

import java.util.List;


@Entity
//@Table(name = "fonction")
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(name = "idx_code_fonction", columnList = "CodeFonction")})
public class Fonction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codeFonction;

    @Column(nullable = false)
    private String libelleFonction;

    @OneToMany(mappedBy = "fonction")
    @JsonManagedReference("agent-fonction")
    private List<Agent> agents;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getLibelleFonction() {
        return libelleFonction;
    }

    public void setLibelleFonction(String libelleFonction) {
        this.libelleFonction = libelleFonction;
    }
}