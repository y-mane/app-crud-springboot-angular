package com.tp_eburtis.tps.service;

import com.tp_eburtis.tps.controller.DepartementDTO;
import com.tp_eburtis.tps.model.Departement;
import com.tp_eburtis.tps.repository.DepartementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test les méthodes du service
 */
@ExtendWith(MockitoExtension.class)
class DepartementServiceTest {
    @Mock
    private DepartementRepository departementRepository;
    @InjectMocks
    private DepartementService departementService = new DepartementService(departementRepository);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test la fonction listerDepartment() du service
     */
    @Test
    void listerDepartementTest() {
        //Given
        Long id1 = 1L;
        Long id2 = 2L;
        Departement departement1 = new Departement("0000F", "Scolaire");
        Departement departement2 = new Departement("1000F", "Juridique");
        List<Departement> listeDepartements = new ArrayList<>();
        listeDepartements.add(departement1);
        listeDepartements.add(departement2);
        when(departementRepository.findAll()).thenReturn(listeDepartements);

        //When
        List<DepartementDTO> departementsObtenus = departementService.listerDepartement();

        //Then
        assertEquals(departement1, listeDepartements.get(0));
        assertEquals(departement2, listeDepartements.get(1));
    }

    /**
     * Test la méthode rechercherDepartement() du service
     */
    @Test
    void rechercherDepartementTest() {
        //Given
        Long id = 1L;
        Departement departement = new Departement("0000F", "Scolaire1");
        when(departementRepository.findById(id)).thenReturn(Optional.of(departement));

        //When
        List<Departement> departementTrouve = departementService.rechercherDepartement(id);

        //Then
        assertEquals(departementTrouve.get(0).getDesignation(), "Scolaire1");
        assertEquals(departement, departementTrouve.get(0));
    }
}
