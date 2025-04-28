package com.example.PFE1.model;

import org.springframework.security.core.GrantedAuthority;

public enum Profils implements GrantedAuthority {
	PROFIL_ADMIN,
	 PROFIL_SUPERVISEUR, 
	 PROFIL_CAISSIER,
	 PROFIL_RAVITAILLEUR;

	  public String getAuthority() {
	    return name();
	  }

}
