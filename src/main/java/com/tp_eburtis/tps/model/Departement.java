package com.tp_eburtis.tps.model;

import jakarta.persistence.*;

/**
 * La classe Departement
 */
@Entity
@Table(name = "Departement")
public class Departement {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String designation;

    public Departement setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Modifie la dédignation du département
     *
     * @param designation la nouvelle désignation
     * @return l'objet
     */
    public Departement setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    /**
     * Ramène le code du département
     *
     * @return le code
     */
    public String getCode() {
        return code;
    }

    /**
     * Ramène la désignation du departement
     *
     * @return la désignation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Constructeur vide
     */
    public Departement() {
        super();
    }

    /**
     * Constructeur avec tous les champs
     *
     * @param id          l'id
     * @param code        le code
     * @param designation la désignation
     */
    public Departement(Long id, String code, String designation) {
        super();
        this.id = id;
        this.code = code;
        this.designation = designation;
    }

    /**
     * Constructeur sans le id
     *
     * @param code        le code
     * @param designation la désignation
     */
    public Departement(String code, String designation) {
        super();
        this.code = code;
        this.designation = designation;
    }

}
