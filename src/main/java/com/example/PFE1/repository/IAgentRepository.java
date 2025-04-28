package com.example.PFE1.repository;

import com.example.PFE1.model.Agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, Long>, JpaSpecificationExecutor<Agent> {
    @Query("SELECT a FROM Agent a WHERE str(a.matricule) LIKE CONCAT(:prefix, '%')")
    List<Agent> findByMatriculeStartingWith(@Param("prefix") String prefix);

    Optional<Agent> findByMatricule(Integer matricule);


}
