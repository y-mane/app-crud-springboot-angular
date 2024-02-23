package com.tp_eburtis.tps.service;

import com.tp_eburtis.tps.controller.PersonneDTO;
import com.tp_eburtis.tps.model.Personne;
import com.tp_eburtis.tps.repository.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Contient les services de Personnes
 */
@Service
public class PersoneService {
    private PersonneRepository personneRepository;

    public PersoneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    /**
     * Ramène la liste de toutes les personnes
     *
     * @return une liste contenant toutes les personnes
     */
    public List<PersonneDTO> listerPersonnes() {
        return personneRepository.findAll()
                .stream()
                .map(PersonneDTO::new)
                .collect(toList());
    }


    /**
     * Ramène une liste contenant la personne par son id
     *
     * @param id l'id de la personne recherchée
     * @return la personne recherchée
     */
    public List<Personne> rechercherPersonne(Long id) {
        return Collections.singletonList(personneRepository.findById(id).orElse(null));
    }

    /**
     * Suprime une personne
     *
     * @param id l'id de la personne à supprimer
     */
    public void supprimerPersonne(Long id) {

        personneRepository.deleteById(id);
    }

    /**
     * Ajoute une nouvelle personne.
     *
     * @param personne la personne à ajouter.
     * @return la personne enregistrée.
     */
    public Personne ajouterPersonne(PersonneDTO personne) {
        Personne nouvellePersonne = new Personne();
        nouvellePersonne.setId(personne.getId());
        nouvellePersonne.setNom(personne.getNom());
        nouvellePersonne.setPrenom(personne.getPrenom());
        nouvellePersonne.setAge(personne.getAge());
        nouvellePersonne.setDepartement(personne.getDepartement());
        //return personneRepository.save(nouvellePersonne);
        try {
            return personneRepository.save(nouvellePersonne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de la personne");
        }
    }

    /**
     * Modifie la personne passée en paramètre
     *
     * @param id       l'id de la personne à modifier
     * @param personne les nouvelles données de la personne
     * @return la personne modifiée
     */
    public Personne modifierPersonne(Long id, PersonneDTO personne) {
        Personne personneMettreAJour = personneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personne introuvable avec ID : " + id));
        //personneMettreAJour.setId(personne.getId());
        personneMettreAJour.setNom(personne.getNom());
        personneMettreAJour.setPrenom(personne.getPrenom());
        personneMettreAJour.setAge(personne.getAge());
        personneMettreAJour.setDepartement(personne.getDepartement());
        try {
            return personneRepository.save(personneMettreAJour);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification de la personne");
        }

    }
}
