package com.example.PFE1.controller;

import com.example.PFE1.model.Agent;
import com.example.PFE1.repository.IAgentRepository;
import com.example.PFE1.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private IAgentService agentService;
    @Autowired
    private IAgentRepository agentRepository;

    @PostMapping("/createAgent")
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        Agent savedAgent = agentService.createAgent(agent);
        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable("id") Long id) {
        Agent agent = agentService.getAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @GetMapping("/getAllAgents")
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agentList = agentService.getAllAgents();
        return new ResponseEntity<>(agentList, HttpStatus.OK);
    }

    @PutMapping("/updateAgent/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable("id") Long agentId, @RequestBody Agent agent) {
        System.out.println("Données reçues: " + agent);
        agent.setId(agentId);
        Agent updatedAgent = agentService.updateAgent(agent);
        return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAgent/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        try {
            agentService.deleteAgent(id);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Agent supprimé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

//    @PostMapping("/Search")
//    public ResponseEntity<List<Agent>> searchAgents(@RequestBody Agent agent) {
//      List<Agent> agentList = agentService.SearchFilter(agent);
//       return new ResponseEntity<>(agentList, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Agent>> searchAgents(
            @RequestParam(required = false) Integer matricule,
            @RequestParam(required = false) String codeDistrict,
            @RequestParam(required = false) String codeFonction,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String numtampon,
            @RequestParam(required = false) String bureauAffectation,
            @RequestParam(required = false) String libelleFonction
    ) {
        return ResponseEntity.ok(agentService.rechercherAgents(
                matricule, codeDistrict, codeFonction, nom, prenom, etat, numtampon, bureauAffectation, libelleFonction
        ));
    }

    @GetMapping("/Search")
    public ResponseEntity<List<Agent>> searchAgents(
            @RequestParam(required = false) Integer matricule,
            @RequestParam(required = false) String codeDistrict,
            @RequestParam(required = false) String codeFonction,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String etatAgent,
            @RequestParam(required = false) Integer numTampon,
            @RequestParam(required = false) String bureauAffectation
    ) {
        List<Agent> results = agentService.searchAgents(matricule, codeDistrict, codeFonction, nom, prenom, etatAgent, numTampon, bureauAffectation);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/autocomplete")
    public List<Integer> autocompleteMatricule(@RequestParam("term") String term) {
        return agentRepository.findByMatriculeStartingWith(term)
                .stream()
                .map(Agent::getMatricule)
                .toList();
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Agent> getByMatricule(@PathVariable("matricule") Integer matricule) {
        Optional<Agent> agent = agentRepository.findByMatricule(matricule);
        return agent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
