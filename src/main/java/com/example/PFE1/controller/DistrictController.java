package com.example.PFE1.controller;

import com.example.PFE1.model.District;
import com.example.PFE1.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private IDistrictService districtService;

    @PostMapping("/createDistrict")
    public District createDistrict(@RequestBody District district) {
        return districtService.createDistrict(district);
    }

    @GetMapping("/{id}")
    public District getDistrictById(@PathVariable Long id) {
        return districtService.getDistrictById(id);
    }

    @GetMapping("/getAllDistricts")
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @PostMapping("/Search")
    public List<District> searchDistricts(@RequestBody District searchCriteria) {
        return districtService.SearchFilter(searchCriteria);
    }

    @PutMapping("/updateDistrict/{id}")
    public District updateDistrict(@PathVariable Long id, @RequestBody District district) {
        district.setId(id);
        return districtService.updateDistrict(district);
    }

    @DeleteMapping("/deleteDistrict/{id}")
    public void deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
    }
}
