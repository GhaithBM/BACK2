package com.example.PFE1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  // Bean pour la configuration CORS
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(ImmutableList.of("*"));
      configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
      configuration.setAllowCredentials(true);  // Nécessaire pour les requêtes avec des informations d'identification
      configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Désactivation de CSRF (pour les API stateless)
    http.csrf().disable();
    http.cors();

    // Spécifie que Spring Security ne gère pas les sessions (stateless)
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Configuration des points d'entrée
    http.authorizeRequests()
        .antMatchers("/api/users/signin").permitAll()
        .antMatchers("/api/users/signup").permitAll()
        .antMatchers("/api/users/getAllUsers").permitAll()
        .antMatchers("/api/users/{userId}").permitAll()
        .antMatchers("/api/users/updateUser/{userId}").permitAll()
        .antMatchers("/api/users/ajouterUser").permitAll()
        .antMatchers("/api/users/rechercherUser").permitAll()
        .antMatchers("/api/users/deleteUser/{userId}").permitAll()
        .antMatchers("/api/users/checkMail").permitAll()
        .antMatchers("/api/email/**").permitAll()
        .antMatchers("/api/agents/**").permitAll()
        .antMatchers("/api/lignes/**").permitAll()
        .antMatchers("/api/bcns/**").permitAll()
        .antMatchers("/api/caissiers/**").permitAll()
        .antMatchers("/api/districts/**").permitAll()
        .antMatchers("/api/feuilles/**").permitAll()
        .antMatchers("/api/fonctions/**").permitAll()
        .antMatchers("/api/groupes/**").permitAll()
        .antMatchers("/api/ligne_bcns/**").permitAll()
        .antMatchers("/api/ligne_feuille/**").permitAll()
        .antMatchers("/api/ligne_ravitaillements/**").permitAll()
        .antMatchers("/api/ravitaillements/**").permitAll()
        .antMatchers("/api/titres/**").permitAll()
        .antMatchers("/api/tours/**").permitAll()
        .antMatchers("/api/typeTitres/**").permitAll()

        
      
               

       
        
       


       
        

        // Ajouter d'autres routes non sécurisées ici
        .anyRequest().authenticated();  // Tout le reste nécessite une authentification

    // Application du filtre JWT
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    // Optionnel, si vous voulez tester l'API via le navigateur
    // http.httpBasic();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // Ignorer certaines ressources comme les fichiers et Swagger
    web.ignoring().antMatchers("/files/**");
    // Si nécessaire, ajouter ici les configurations Swagger (décommenter si applicable)
    /*
    web.ignoring().antMatchers(
        "/v2/api-docs",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/configuration/**",
        "/webjars/**",
        "/public"
    );
    */
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);  // Le facteur de travail peut être ajusté
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
}
