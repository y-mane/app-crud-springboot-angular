package com.tp_eburtis.tps.controller;


import com.tp_eburtis.tps.model.Departement;
import com.tp_eburtis.tps.model.Personne;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class PersonneDTO {
    private Long id;
    @NotNull
    private String nom;
    private String prenom;
    private int age;

    public Departement getDepartement() {
        return departement;
    }

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;


    /**
     * Ramène l'id de l'instance DTO
     *
     * @return l'id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ramène le nom de l'instance DTO
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Contructeur qui prend la personne en param et ramène un DTO
     *
     * @param personne la personne dont on ramène le DTO
     */
    public PersonneDTO(Personne personne) {
        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenom = personne.getPrenom();
        this.age = personne.getAge();
        this.departement = personne.getDepartement();
    }

    /**
     * Ramène le prenom de l'instance DTO
     *
     * @return le prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Ramène l'age de l'instance DTO
     *
     * @return l'age
     */
    public int getAge() {
        return age;
    }

}
