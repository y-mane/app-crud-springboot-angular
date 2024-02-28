package com.tp_eburtis.tps.controller;

import com.tp_eburtis.tps.model.Departement;
import jakarta.persistence.*;

@Entity
@Table(name = "Departement")
public class DepartementDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    public Long getId() {
        return id;
    }

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
    public DepartementDTO(Departement departement){
        this.id = departement.getId();
        this.code = departement.getCode();
        this.designation = departement.getDesignation();
    }

}
