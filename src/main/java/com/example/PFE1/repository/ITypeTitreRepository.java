package com.example.PFE1.repository;

import com.example.PFE1.model.TypeTitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ITypeTitreRepository extends JpaRepository<TypeTitre, Long> , JpaSpecificationExecutor<TypeTitre>{
}