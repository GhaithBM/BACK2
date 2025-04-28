package com.example.PFE1.repository;

import com.example.PFE1.model.Ravitaillement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IRavitaillementRepository extends JpaRepository<Ravitaillement, Long> {

    //requête JPQL
    @Query("SELECT r FROM Ravitaillement r WHERE " +
            "(:matricule IS NULL OR r.matricule = :matricule) AND " +
            "(:nom IS NULL OR r.nom LIKE %:nom%) AND " +
            "(:fonction IS NULL OR r.fonction LIKE %:fonction%) AND " +
            "(:affectation IS NULL OR r.affectation LIKE %:affectation%) AND " +
            "(:codeDistrict IS NULL OR r.codeDistrict LIKE %:codeDistrict%) AND " +
            "(:date IS NULL OR r.date = :date) AND " +
            "(:sequence IS NULL OR r.sequence LIKE %:sequence%)")
    List<Ravitaillement> findByCriteria(
            @Param("matricule") Integer matricule, // Utilise Integer si tu veux permettre null
            @Param("nom") String nom,
            @Param("fonction") String fonction,
            @Param("affectation") String affectation,
            @Param("codeDistrict") String codeDistrict,
            @Param("date") Date date,
            @Param("sequence") String sequence
    );
    @Query("SELECT DISTINCT r.matricule FROM Ravitaillement r")
    List<Integer> findAllMatricules();

    @Query("SELECT DISTINCT r.codeDistrict FROM Ravitaillement r")
    List<String> findAllCodeDistricts();

    @Query("SELECT DISTINCT r.codeDistrict FROM Ravitaillement r WHERE LOWER(r.codeDistrict) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<String> findDistinctCodeDistrictByCodeDistrictContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT DISTINCT CAST(r.matricule AS string) FROM Ravitaillement r WHERE CAST(r.matricule AS string) LIKE %:query%")
    List<String> findMatriculesLike(@Param("query") String query);


}
