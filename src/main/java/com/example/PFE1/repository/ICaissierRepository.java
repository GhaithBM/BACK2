package com.example.PFE1.repository;


import com.example.PFE1.model.Caissier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICaissierRepository extends JpaRepository<Caissier, Long> {

    @Query("SELECT c FROM Caissier c WHERE " +
            "(:mle IS NULL OR c.mle = :mle) AND " +
            "(:libelleCaissier IS NULL OR c.libelleCaissier = :libelleCaissier) AND " +
            "(:codeCaissier IS NULL OR c.codeCaissier = :codeCaissier) AND " +
            "(:codeDistrict IS NULL OR c.codeDistrict = :codeDistrict)")
    List<Caissier> searchCaissiers(String mle, String libelleCaissier, String codeCaissier, String codeDistrict);

}

