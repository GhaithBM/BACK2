package com.example.PFE1.repository;

import com.example.PFE1.model.FeuilleDeRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IFeuilleDeRouteRepository extends JpaRepository<FeuilleDeRoute, Long> {

        // Remove this method if 'titre' is no longer a field
        // List<FeuilleDeRoute> findByTitreContaining(String titre);

        @Query("SELECT f FROM FeuilleDeRoute f WHERE " +
                "(:typeVente IS NULL OR f.typeVente LIKE %:typeVente%) AND " +
                "(:matricule IS NULL OR f.matricule = :matricule) AND " +
                "(:district IS NULL OR f.district LIKE %:district%) AND " +
                "(:dateVersement IS NULL OR f.dateVersement = :dateVersement) AND " +
                "(:dateVente IS NULL OR f.dateVente = :dateVente)")
        List<FeuilleDeRoute> findByCriteria(
                @Param("typeVente") String typeVente,
                @Param("matricule") Integer matricule,
                @Param("district") String district,
                @Param("dateVersement") Date dateVersement,
                @Param("dateVente") Date dateVente);
    }

