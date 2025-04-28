package com.example.PFE1.repository;

import com.example.PFE1.model.Bcn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBcnRepository extends JpaRepository<Bcn, Long> {

    @Query("SELECT b FROM Bcn b WHERE " +
            "(:numeroBcn IS NULL OR CAST(b.numeroBcn AS string) LIKE %:numeroBcn%) AND " +
            "(:codeDistrict IS NULL OR b.codeDistrict LIKE %:codeDistrict%) AND " +
            "(:dateBcn IS NULL OR b.dateBcn = :dateBcn)")
    List<Bcn> findByCriteria(
            @Param("numeroBcn") String numeroBcn,
            @Param("codeDistrict") String codeDistrict,
            @Param("dateBcn") Date dateBcn);
}

