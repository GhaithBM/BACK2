package com.example.PFE1.model;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user") // Renommé pour éviter le conflit avec le mot-clé réservé "user"
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")// ID auto-incrémenté
    private Long idUser;

    private String nom;
    private String prenom;
    private String matricule;

    @Column(unique = true, nullable = false) // Empêche les doublons d'email
    private String email;

    private String password;

    @Column(unique = true, nullable = false) // CIN unique
    private String cin;

    private String mobile;
    private String profil;

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Profils> profils = new ArrayList<>();

}