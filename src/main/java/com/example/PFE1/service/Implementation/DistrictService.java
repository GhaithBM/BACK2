package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.District;
import com.example.PFE1.repository.IDistrictRepository;
import com.example.PFE1.service.IDistrictService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private IDistrictRepository districtRepository;

    @Override
    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public District getDistrictById(Long id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        return optionalDistrict.orElse(null);
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> SearchFilter(District district) {
        Specification<District> spec = Specification.where(null);

        if (district.getCodeDistrict() != null && !district.getCodeDistrict().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("codeDistrict"), district.getCodeDistrict()));
        }

        if (district.getLibelleDistrict() != null && !district.getLibelleDistrict().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("libelleDistrict"), district.getLibelleDistrict()));
        }

        if (district.getLibelleDistrictAR() != null && !district.getLibelleDistrictAR().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("libelleDistrictAR"), district.getLibelleDistrictAR()));
        }

        if (district.getReseaux() != null && !district.getReseaux().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("reseaux"), district.getReseaux()));
        }

        return districtRepository.findAll(spec);
    }


    @Override
    public District updateDistrict(District district) {
        Optional<District> existingDistrictOptional = districtRepository.findById(district.getId());
        if (existingDistrictOptional.isPresent()) {
            District existingDistrict = existingDistrictOptional.get();
            existingDistrict.setCodeDistrict(district.getCodeDistrict());
            existingDistrict.setLibelleDistrict(district.getLibelleDistrict());
            existingDistrict.setLibelleDistrictAR(district.getLibelleDistrictAR());
            existingDistrict.setReseaux(district.getReseaux());
            return districtRepository.save(existingDistrict);
        }
        return null;
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }
}
