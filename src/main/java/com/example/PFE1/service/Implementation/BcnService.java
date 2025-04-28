package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.Bcn;
import com.example.PFE1.model.District;
import com.example.PFE1.model.LigneBcn;
import com.example.PFE1.repository.IBcnRepository;
import com.example.PFE1.repository.IDistrictRepository;
import com.example.PFE1.repository.ILigneBcnRepository;
import com.example.PFE1.service.IBcnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BcnService implements IBcnService {

    private static final Logger logger = LoggerFactory.getLogger(BcnService.class);

    @Autowired
    private IBcnRepository bcnRepository;

    @Autowired
    private ILigneBcnRepository ligneBcnRepository;

    @Autowired
    private IDistrictRepository districtRepository;

    public List<Bcn> getAllBcns() {
        return bcnRepository.findAll();
    }

    public Bcn getBcnById(Long id) {
        return bcnRepository.findById(id).orElse(null);
    }

    public void deleteBcn(Long id) {
        bcnRepository.deleteById(id);
    }

    public List<Bcn> getBcnsByCriteria(String numeroBcn, Date dateBcn, String codeDistrict) {
        return bcnRepository.findByCriteria(numeroBcn, codeDistrict, dateBcn);
    }

    public Bcn saveBcn(Bcn bcn) {
        if (bcn.getCodeDistrict() != null) {
            District district = districtRepository.findByCodeDistrict(bcn.getCodeDistrict());
            if (district == null) {
                throw new RuntimeException("District not found with code: " + bcn.getCodeDistrict());
            }
            bcn.setDistrict(district);
        }

        if (bcn.getLignes() != null) {
            for (LigneBcn ligne : bcn.getLignes()) {
                ligne.setBcn(bcn);
            }
        }

        return bcnRepository.save(bcn);
    }

    public Bcn updateBcn(Long id, Bcn bcnDetails) {
        Bcn bcn = bcnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BCN not found"));

        bcn.setNumeroBcn(bcnDetails.getNumeroBcn());
        bcn.setDateBcn(bcnDetails.getDateBcn());
        bcn.setCodeDistrict(bcnDetails.getCodeDistrict());

        if (bcnDetails.getCodeDistrict() != null) {
            District district = districtRepository.findByCodeDistrict(bcnDetails.getCodeDistrict());
            if (district == null) {
                throw new RuntimeException("District not found with code: " + bcnDetails.getCodeDistrict());
            }
            bcn.setDistrict(district);
        } else {
            bcn.setDistrict(null);
        }

        List<LigneBcn> updatedLignes = bcnDetails.getLignes();
        if (updatedLignes != null) {
            bcn.getLignes().removeIf(ligne -> !updatedLignes.contains(ligne));
            for (LigneBcn ligne : updatedLignes) {
                ligne.setBcn(bcn);
                if (!bcn.getLignes().contains(ligne)) {
                    bcn.getLignes().add(ligne);
                }
            }
        } else {
            bcn.setLignes(List.of());
        }

        return bcnRepository.save(bcn);
    }
}
