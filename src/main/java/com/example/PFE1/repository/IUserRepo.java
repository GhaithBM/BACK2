package com.example.PFE1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PFE1.model.User;



@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
	Optional<List<User>> findByNom(String nom);
    List<User> findByNomContainingOrPrenomContaining(String nom, String prenom);

	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email,String password);
	boolean existsByEmail(String email);
	Optional<User> findByPassword(String password);


}
