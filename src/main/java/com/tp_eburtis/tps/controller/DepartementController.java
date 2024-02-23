package com.tp_eburtis.tps.controller;

import com.tp_eburtis.tps.model.Departement;
import com.tp_eburtis.tps.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour gérer les actions sur la classe Departement
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    /**
     * Ramène la liste des départements
     *
     * @return la liste des départements
     */
    @GetMapping("/departements")
    public List<Departement> listeDepartement() {
        return departementService.listerDepartement();
    }

    /**
     * Ramène un département
     *
     * @param id l'id du département recherchée
     * @return le département recherché
     */
    @GetMapping("/departement/{id}")
    public List<Departement> unDepartement(@PathVariable Long id) {
        return departementService.rechercherDepartement(id);
    }
}

