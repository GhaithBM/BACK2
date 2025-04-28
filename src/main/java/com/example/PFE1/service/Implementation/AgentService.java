package com.example.PFE1.service.Implementation;


import com.example.PFE1.model.Agent;
import com.example.PFE1.repository.IAgentRepository;
import com.example.PFE1.service.IAgentService;
import javax.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AgentService implements IAgentService {

    @Autowired
    private IAgentRepository agentRepository;

    @Override
    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent getAgentById(Long id) {
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        return optionalAgent.orElse(null);
    }

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

//    @Override
//    public List<Agent> SearchFilter(Agent agent) {
//        Specification<Agent> spec = new Specification<Agent>() {
//            @Override
//            public Specification<Agent> and(Specification<Agent> other) {
//                return Specification.super.and(other);
//            }
//
//            @Override
//            public Specification<Agent> or(Specification<Agent> other) {
//                return Specification.super.or(other);
//            }
//
//
//            public Predicate toPredicate(Root<Agent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate predicate = criteriaBuilder.conjunction();
//
//                // Vérifiez que la casse correspond à celle de l'entité
//                if (agent.getMatricule() != 0) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("matricule"), agent.getMatricule()));
//                }
//                if (agent.getCodeDistrict() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("codeDistrict"), agent.getCodeDistrict()));
//                }
//                if (agent.getCodeFonction() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("codeFonction"), agent.getCodeFonction()));
//                }
//                if (agent.getNom() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("nom"), agent.getNom()));  // Correction ici si "nom" au lieu de "Nom"
//                }
//                if (agent.getPrenom() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("prenom"), agent.getPrenom()));
//                }
//                if (agent.getEtatAgent() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("etatAgent"), agent.getEtatAgent()));
//                }
//                if (agent.getNumTampon() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("numTampon"), agent.getNumTampon()));
//                }
//                if (agent.getBureauAffectation() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("bureauAffectation"), agent.getBureauAffectation()));
//                }
//
//                return predicate;
//            }
//        };
//        return agentRepository.findAll(spec);
//    }

    @Override
    public Agent updateAgent(Agent agent) {
        Optional<Agent> existingAgentOptional = agentRepository.findById(agent.getId());
        if (existingAgentOptional.isPresent()) {
            Agent existingAgent = existingAgentOptional.get();

            // Mise à jour des propriétés de l'agent
            existingAgent.setCodeDistrict(agent.getCodeDistrict());
            existingAgent.setCodeFonction(agent.getCodeFonction());
            existingAgent.setNom(agent.getNom());
            existingAgent.setPrenom(agent.getPrenom());
            existingAgent.setEtatAgent(agent.getEtatAgent());
            existingAgent.setNumTampon(agent.getNumTampon());
            existingAgent.setBureauAffectation(agent.getBureauAffectation());

            return agentRepository.save(existingAgent);
        }
        return null; // Agent non trouvé
    }

    @Override
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public List<Agent> rechercherAgents(Integer matricule, String codeDistrict, String codeFonction, String nom,
                                        String prenom, String etat, String numtampon, String bureauAffectation,
                                        String libelleFonction) {

        Specification<Agent> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> fonctionJoin = null;

            if ((codeFonction != null && !codeFonction.isEmpty()) || (libelleFonction != null && !libelleFonction.isEmpty())) {
                fonctionJoin = root.join("fonction", JoinType.LEFT);
            }

            if (matricule != null) {
                predicates.add(cb.like(cb.lower(cb.concat(root.get("matricule").as(String.class), "")), "%" + matricule.toString().toLowerCase() + "%"));
            }

            if (codeDistrict != null && !codeDistrict.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("codeDistrict")), "%" + codeDistrict.toLowerCase() + "%"));
            }
            if (nom != null && !nom.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nom")), "%" + nom.toLowerCase() + "%"));
            }
            if (prenom != null && !prenom.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("prenom")), "%" + prenom.toLowerCase() + "%"));
            }
            if (etat != null && !etat.trim().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("etatAgent")), etat.toLowerCase()));
            }
            if (numtampon != null && !numtampon.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(cb.concat(root.get("numTampon").as(String.class), "")), "%" + numtampon.toLowerCase() + "%"));
            }
            if (bureauAffectation != null && !bureauAffectation.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("bureauAffectation")), "%" + bureauAffectation.toLowerCase() + "%"));
            }

            if (codeFonction != null && !codeFonction.trim().isEmpty() && fonctionJoin != null) {
                predicates.add(cb.like(cb.lower(fonctionJoin.get("codeFonction")), "%" + codeFonction.toLowerCase() + "%"));
            }
            if (libelleFonction != null && !libelleFonction.trim().isEmpty() && fonctionJoin != null) {
                predicates.add(cb.like(cb.lower(fonctionJoin.get("libelle")), "%" + libelleFonction.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return agentRepository.findAll(spec);
    }


    public List<Agent> searchAgents(Integer matricule, String codeDistrict, String codeFonction,
                                    String nom, String prenom, String etatAgent,
                                    Integer numTampon, String bureauAffectation) {

        Specification<Agent> spec = Specification.where(null);

        if (matricule != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("matricule"), matricule));
        }

        if (codeDistrict != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("codeDistrict"), codeDistrict));
        }

        if (codeFonction != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("codeFonction"), codeFonction));
        }

        if (nom != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nom")), "%" + nom.toLowerCase() + "%"));
        }

        if (prenom != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("prenom")), "%" + prenom.toLowerCase() + "%"));
        }

        if (etatAgent != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("etatAgent"), etatAgent));
        }

        if (numTampon != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("numTampon"), numTampon));
        }

        if (bureauAffectation != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("bureauAffectation")), "%" + bureauAffectation.toLowerCase() + "%"));
        }

        return agentRepository.findAll(spec);
    }

}

