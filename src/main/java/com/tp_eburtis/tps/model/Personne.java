package com.tp_eburtis.tps.model;


import jakarta.persistence.*;

/**
 * La classe Personne
 */
@Entity
@Table(name = "Personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private int age;

    /**
     * Ramène le departement de l'instance
     *
     * @return le departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Modifie le département de l'instance
     *
     * @param departement le nouveau departement
     * @return l'objet
     */
    public Personne setDepartement(Departement departement) {
        this.departement = departement;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;


    /**
     * Ramène l'id de la personne
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'id de la personne
     *
     * @param id le nouvel id
     * @return l'objet
     */
    public Personne setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Modifie le nom de la personne
     *
     * @param nom le nouveau nom
     * @return l'objet
     */
    public Personne setNom(String nom) {
        this.nom = nom;
        return this;
    }

    /**
     * Modifie le prenom de la personne
     *
     * @param prenom le nouveau prenom
     * @return l'objet
     */
    public Personne setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    /**
     * Modifie l'age de la personne
     *
     * @param age le nouvel age
     * @return l'objet
     */
    public Personne setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Ramène le nom de la personne
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Ramène le prenom de la personne
     *
     * @return le prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Ramène l'age de la personne
     *
     * @return l'age
     */
    public int getAge() {
        return age;
    }

    /**
     * Constructeur vide
     */
    public Personne() {
        super();
    }

    /**
     * Constructeur avec paramètre sans le id
     *
     * @param nom         le nom
     * @param prenom      le prenom
     * @param age         l'age
     * @param departement le departement
     */
    public Personne(String nom, String prenom, int age, Departement departement) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.departement = departement;
    }

    /**
     * Constructeur avec paramètre avec le id
     *
     * @param nom         le nom
     * @param prenom      le prenom
     * @param age         l'age
     * @param departement le departement
     */
    public Personne(Long id, String nom, String prenom, int age, Departement departement) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.departement = departement;
    }
}
