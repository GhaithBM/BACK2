package com.example.PFE1.service;

import com.example.PFE1.model.FeuilleDeRoute;

import java.util.Date;
import java.util.List;

public interface IFeuilleDeRouteService {

    List<FeuilleDeRoute> getAllFeuillesDeRoute();

    FeuilleDeRoute getFeuilleDeRouteById(Long id);

    void deleteFeuilleDeRoute(Long id);

    List<FeuilleDeRoute> getFeuillesByCriteria(String typeVente, Integer matricule, String district, Date dateVersement, Date dateVente);

    FeuilleDeRoute saveFeuilleDeRoute(FeuilleDeRoute feuille);

    FeuilleDeRoute updateFeuilleDeRoute(Long id, FeuilleDeRoute feuilleDetails);
}
