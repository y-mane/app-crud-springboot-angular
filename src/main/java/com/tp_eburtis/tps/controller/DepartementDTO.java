package com.tp_eburtis.tps.controller;

import jakarta.persistence.*;

@Entity
@Table(name = "Departement")
public class DepartementDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String designation;

    /**
     * Modifie le code du departement DTO
     *
     * @param code le nouveau code
     * @return l'objet
     */
    public DepartementDTO setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Modifie la désignation du departement DTO
     *
     * @param designation la nouvelle désignationn
     * @return l'abjet
     */
    public DepartementDTO setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    /**
     * Ramène le code du departement DTO
     *
     * @return le code
     */
    public String getCode() {
        return code;
    }

    /**
     * Ramène la désignation du département DTO
     *
     * @return la désignation
     */
    public String getDesignation() {
        return designation;
    }


    public DepartementDTO() {
        super();
    }

}
