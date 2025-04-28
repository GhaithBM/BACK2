package com.example.PFE1.repository;

import com.example.PFE1.model.LigneBcn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILigneBcnRepository extends JpaRepository<LigneBcn, Long> {
}
