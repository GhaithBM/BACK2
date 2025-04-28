package com.example.PFE1.service.Implementation;

import com.example.PFE1.model.LigneBcn;
import com.example.PFE1.repository.ILigneBcnRepository;
import com.example.PFE1.service.ILigneBcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneBcnService implements ILigneBcnService {

    @Autowired
    private ILigneBcnRepository repository;

    public LigneBcn saveLigneBcn(LigneBcn ligneBcn) {
        return repository.save(ligneBcn);
    }

    public List<LigneBcn> getAllLigneBcns() {
        return repository.findAll();
    }

    public LigneBcn getLigneBcnById(Long id) {
        Optional<LigneBcn> ligneBcn = repository.findById(id);
        return ligneBcn.orElse(null);
    }

    public LigneBcn updateLigneBcn(Long id, LigneBcn ligneBcnDetails) {
        LigneBcn ligneBcn = getLigneBcnById(id);
        if (ligneBcn != null) {
            ligneBcn.setAbreviation(ligneBcnDetails.getAbreviation());
            ligneBcn.setTitre(ligneBcnDetails.getTitre());
            ligneBcn.setLibelle(ligneBcnDetails.getLibelle());
            ligneBcn.setSerie(ligneBcnDetails.getSerie());
            ligneBcn.setDebut(ligneBcnDetails.getDebut());
            ligneBcn.setFin(ligneBcnDetails.getFin());
            ligneBcn.setQuantite(ligneBcnDetails.getQuantite());
            return repository.save(ligneBcn);
        }
        return null;
    }

    public void deleteLigneBcn(Long id) {
        repository.deleteById(id);
    }
}
