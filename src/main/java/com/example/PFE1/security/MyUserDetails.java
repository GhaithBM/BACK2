package com.example.PFE1.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.PFE1.model.User;
import com.example.PFE1.repository.IUserRepo;



@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private IUserRepo userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findByEmail(email);

    // Vérifie si l'utilisateur existe dans la base de données
    User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email '" + email + "' not found"));

    // Conversion des rôles en autorités GrantedAuthority
    Collection<? extends GrantedAuthority> authorities = user.getProfils();

    // Retourne un objet UserDetails de Spring Security avec les informations de l'utilisateur
    return org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(user.getPassword())
        .authorities(authorities)
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}
