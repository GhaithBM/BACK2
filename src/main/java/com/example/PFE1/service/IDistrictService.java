package com.example.PFE1.service;

import com.example.PFE1.model.District;

import java.util.List;

public interface IDistrictService {
    District createDistrict(District district);
    District getDistrictById(Long id);
    List<District> getAllDistricts();
    List<District> SearchFilter(District district);
    District updateDistrict(District district);
    void deleteDistrict(Long id);
}