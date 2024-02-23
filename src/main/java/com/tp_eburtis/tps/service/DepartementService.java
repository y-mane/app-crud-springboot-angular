package com.tp_eburtis.tps.service;

import com.tp_eburtis.tps.model.Departement;
import com.tp_eburtis.tps.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }


    /**
     * Ramène la liste des départements
     *
     * @return la liste contenant tous les départements
     */
    public List<Departement> listerDepartement() {
        List<Departement> departements = new ArrayList<>();
        departements.addAll(departementRepository.findAll());
        return departements;
    }

    /**
     * Ramène un départements dont l'id est passé en param
     *
     * @param id l'id du département recherché
     * @return le département recherché
     */
    public List<Departement> rechercherDepartement(Long id) {
        return Collections.singletonList(departementRepository.findById(id).orElse(null));
    }

}
