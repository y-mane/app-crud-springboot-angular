package com.tp_eburtis.tps.model;

import ch.qos.logback.core.testUtil.MockInitialContext;

/**
 * Classe Calcul
 */
public class Calcul {
    private float a;
    private float b;

    /**
     * Constructeur vide
     */
    public Calcul() {
    }

    /**
     * Constructeur avec param
     *
     * @param a le premier nombre
     * @param b le deuxieme nombre
     */
    public Calcul(float a, float b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Addtionne deux valeurs
     *
     * @param a la premiere valeur
     * @param b la deuxieme valeur
     * @return le resultat de l'addition
     */
    public float additionner(float a, float b) {
        return a + b;
    }

    /**
     * Soustrait une valeur d'une autre
     *
     * @param a la valeur de laquelle on ote
     * @param b la valeur à oter
     * @return le résultat de la soustraction
     */
    public float soustraire(float a, float b) {
        return a - b;
    }

    /**
     * Multiplie deux valeurs
     *
     * @param a la premiere valeur
     * @param b la deuxieme valeur
     * @return le résultat de la multiplication
     */
    public float multiplier(float a, float b) {
        return a * b;
    }

    /**
     * Divise une valeur avec une autre
     *
     * @param a la valeur divisée
     * @param b le diviseur
     * @return le résultat de la division
     * @throws Exception
     */
    public float diviser(float a, float b) throws Exception {
        if (b != 0) {
            return a / b;
        }
        throw new Exception();
    }

    /**
     * Calcul le carré d'une valeur
     *
     * @param a la valeur
     * @return le carré
     */
    public float carre(float a) {
        return multiplier(a, a);
    }

    /**
     * Calcul (a+b)2
     *
     * @param a a
     * @param b b
     * @return le résultat
     */
    public float identiteRemarquable(float a, float b) {
        float a2 = multiplier(a, a);
        float b2 = multiplier(b, b);
        float ax2 = multiplier(2, a);
        return additionner(additionner(a2, multiplier(ax2, b)), b2);
    }
}
