package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.Caissier;
import com.example.PFE1.model.District;
import com.example.PFE1.repository.ICaissierRepository;
import com.example.PFE1.repository.IDistrictRepository;
import com.example.PFE1.service.ICaissierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaissierService implements ICaissierService {

    @Autowired
    private ICaissierRepository caissierRepository;

    private final IDistrictRepository districtRepository;

    public CaissierService(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public Caissier createCaissier(Caissier caissier) {
        if (caissier.getCodeDistrict() != null) {
            District district = districtRepository.findByCodeDistrict(caissier.getCodeDistrict());
            caissier.setDistrict(district);
        }
        return caissierRepository.save(caissier);
    }

    @Override
    public Caissier getCaissierById(Long id) {
        Optional<Caissier> optionalCaissier = caissierRepository.findById(id);
        return optionalCaissier.orElse(null);
    }

    @Override
    public List<Caissier> getAllCaissiers() {
        return caissierRepository.findAll();
    }

    // ✅ Nouvelle méthode de recherche sans Specification
    @Override
    public List<Caissier> SearchFilter(Caissier caissier) {
        String mle = caissier.getMle();
        String libelleCaissier = caissier.getLibelleCaissier();
        String codeCaissier = caissier.getCodeCaissier();
        String codeDistrict = caissier.getCodeDistrict();

        return caissierRepository.searchCaissiers(mle, libelleCaissier, codeCaissier, codeDistrict);
    }

    @Override
    public Caissier updateCaissier(Caissier caissier) {
        Optional<Caissier> existingCaissierOptional = caissierRepository.findById(caissier.getId());
        if (existingCaissierOptional.isPresent()) {
            Caissier existingCaissier = existingCaissierOptional.get();
            existingCaissier.setMle(caissier.getMle());
            existingCaissier.setCodeCaissier(caissier.getCodeCaissier());
            existingCaissier.setLibelleCaissier(caissier.getLibelleCaissier());
            existingCaissier.setDistrict(caissier.getDistrict());
            return caissierRepository.save(existingCaissier);
        }
        return null;
    }

    @Override
    public void deleteCaissier(Long id) {
        caissierRepository.deleteById(id);
    }
}
