package com.tp_eburtis.tps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.*;

public class CalculTest {

    private Calcul calcul = new Calcul();

    @Test
    void testAdditionner() {
        //Given
        float a = 5f;
        float b = 5f;

        //When
        float result = calcul.additionner(a,b);

        //Then
        assertEquals (10f, result);

    }

    @Test
    void soustraire() {
        //Given
        float a = 5f;
        float b = 2f;
        //When
        float result = this.calcul.soustraire(a,b);
        //Then
        assertEquals(3f,result);
    }

    @Test
    void multiplier() {
        //Given
        float a = 8f;
        float b = 3f;
        //When
        float result = this.calcul.multiplier(a,b);
        //Then
        assertEquals(24f,result);
    }

    @Test
    void diviser() throws Exception{
        //Given
        float a = 10f;
        float b = 2f;
        //When
        float result = calcul.diviser(a,b);
        //Then
        assertEquals(5f,result);
    }

    @Test
    void carre() {
        //Given
        float a = 2;
        //When
        float result = this.calcul.carre(a);
        //Then
        assertEquals(4,result);
    }

    @Test
    void identiteRemarquable() {
        //Given

        float a = 2f;
        float b = 3f;

        //When

        float result = calcul.identiteRemarquable(a,b);

        //Then

        assertEquals(25,result);
    }
}
