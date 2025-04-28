package com.example.PFE1.repository;

import com.example.PFE1.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface IDistrictRepository extends JpaRepository<District, Long> ,JpaSpecificationExecutor<District>{
    District findByCodeDistrict(String codeDistrict);
}
