package com.tp_eburtis.tps.controller;


import com.tp_eburtis.tps.model.Personne;
import com.tp_eburtis.tps.service.PersoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour gérer les actions sur la classe Personne
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PersonneController {
    @Autowired
    private PersoneService personneService;

    /**
     * Ramène la liste des personnes
     *
     * @return
     */
    @GetMapping("/personnes")
    public List<PersonneDTO> listePersonnes() {
        return personneService.listerPersonnes();
    }

    /**
     * Recherche une personne par son id
     *
     * @param id l'id de la personne recherchée
     * @return un liste contenant la personne recherchée
     */
    @GetMapping("/personne/{id}")
    public List<Personne> unePersonne(@PathVariable Long id) {
        return personneService.rechercherPersonne(id);
    }

    /**
     * Ajoute une nouvelle instance de personne
     *
     * @param personne la nouvelle personne à ajouter
     * @return la nouvelle personne
     */
    @PostMapping("/ajouter")
    public String ajouterPersonne(@RequestBody PersonneDTO personne) {
        Personne nouvellePersonne = personneService.ajouterPersonne(personne);
        return nouvellePersonne != null ? "Personne ajouter avec succès !" : "Erreur lors de l'ajout";
    }

    /**
     * Modifie une personne déjà existante
     *
     * @param personne les nouvelles données de la personne
     * @param id       l'id de la personne à modifier
     * @return la personne modifiée
     */
    @PutMapping("/modifier/{id}")
    public String modifierPersonne(@RequestBody PersonneDTO personne, @PathVariable Long id) {
        Personne personneMettreAjour = personneService.modifierPersonne(id, personne);
        return personneMettreAjour != null ? "Modifiée avec succès !" : "Erreur lors de la modification";
    }

    /**
     * Supprime une personne
     *
     * @param id l'id de la personne à supprimer
     */
    @DeleteMapping("/supprimer/{id}")
    public void supprimerPersonne(@PathVariable Long id) {
        personneService.supprimerPersonne(id);
    }
}
