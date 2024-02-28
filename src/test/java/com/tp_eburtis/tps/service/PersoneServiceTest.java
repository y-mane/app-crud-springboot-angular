package com.tp_eburtis.tps.service;

import com.tp_eburtis.tps.controller.PersonneDTO;
import com.tp_eburtis.tps.model.Departement;
import com.tp_eburtis.tps.model.Personne;
import com.tp_eburtis.tps.repository.DepartementRepository;
import com.tp_eburtis.tps.repository.PersonneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Test les méthodes du service
 */
@ExtendWith(MockitoExtension.class)
class PersoneServiceTest {
    @Mock
    private PersonneRepository personneRepository = new PersonneRepository() {
        @Override
        public void flush() {

        }

        @Override
        public <S extends Personne> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Personne> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Personne> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Personne getOne(Long aLong) {
            return null;
        }

        @Override
        public Personne getById(Long aLong) {
            return null;
        }

        @Override
        public Personne getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Personne> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Personne> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Personne> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public List<Personne> findAll() {
            return null;
        }

        @Override
        public List<Personne> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public <S extends Personne> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Personne> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Personne entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Personne> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<Personne> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Personne> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Personne> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Personne> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Personne> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Personne> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Personne, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
    @InjectMocks
    private PersoneService personneService = new PersoneService(personneRepository);
    @InjectMocks
    private DepartementService departementService;
    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test la fonction listerPersonnes() du service
     */
    @Test
    void listerPersonnesTest() {
        //Given
        Long id1 = 1L;
        Long id2 = 2L;
        Departement departement1 = new Departement("0000F", "Scolaire");
        Departement departement2 = new Departement("1000F", "Juridique");
        Personne personne1 = new Personne(id1, "Irie", "Konan", 25, departement1);
        Personne personne2 = new Personne(id2, "Irie2", "Konan2", 25, departement2);

        List<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(personne1);
        listePersonnes.add(personne2);
        when(personneRepository.findAll()).thenReturn(listePersonnes);

        //When
        List<PersonneDTO> personnesObetenues = personneService.listerPersonnes();

        //Then
        assertEquals("Irie", personnesObetenues.get(0).getNom());
        assertEquals("Irie2", personnesObetenues.get(1).getNom());
        //assertEquals(listePersonnesDTO,personnesObetenues);
    }

    /**
     * Test la fonction rechercherPersonne() du service
     */
    @Test
    void rechercherPersonneTest() {
        //Given
        Long id = 1L;
        Departement departement = new Departement("0000F", "Scolaire1");
        Personne personne = new Personne(
                id, "Irie", "Konan", 25, departement
        );
        when(personneRepository.findById(id)).thenReturn(Optional.of(personne));

        //When
        List<Personne> personneObtenue = personneService.rechercherPersonne(id);

        //Then
        assertEquals(personne.getNom(), personneObtenue.get(0).getNom());
        assertEquals(personne, personneObtenue.get(0));
    }

    /**
     * Test la fonction supprimerPersonne() du service
     *
     * @throws ParseException
     */
    @Test
    void supprimerPersonneTest() throws ParseException {
        //Given
        Long id = 1L;
        doNothing().when(personneRepository).deleteById(id);

        //When
        personneService.supprimerPersonne(id);

        //Then
        verify(personneRepository, times(1)).deleteById(id);
    }

    /**
     * Test la fonction ajouterPersonne() du service
     */
    @Test
    void ajouterPersonneTest() {
        //Given
        Long id = 1L;
        Departement departement = new Departement(id, "7000F", "Scolaire");
        Personne personne = new Personne("Irie", "Konan", 25, departement);
        PersonneDTO personneDTO = new PersonneDTO(personne);
        when(personneRepository.save(any(Personne.class))).thenReturn(personne);

        //When
        Personne nouvellePersonne = personneService.ajouterPersonne(personneDTO);

        //Then
        assertEquals(personne.getNom(), nouvellePersonne.getNom());
        assertEquals(personne.getPrenom(), nouvellePersonne.getPrenom());
        assertEquals(personne.getAge(), nouvellePersonne.getAge());
        assertEquals(personne.getDepartement(), nouvellePersonne.getDepartement());
    }

    /**
     * Test la fonction modifierPersonne() du service
     */
    @Test
    void modifierPersonneTest() {
        //Given
        Long id = 1L;
        Departement departement = new Departement(id, "7000F", "Scolaire");
        Personne personneAModifier = new Personne(id, "Keita", "Souleymane", 23, departement);

        PersonneDTO personneModifieeDTO = new PersonneDTO(personneAModifier);
        when(personneRepository.findById(id)).thenReturn(Optional.of(personneAModifier));
        when(personneRepository.save(personneAModifier)).thenReturn(personneAModifier);

        //When
        Personne personneModifee = personneService.modifierPersonne(id, personneModifieeDTO);

        //Then
        assertEquals("Keita", personneModifee.getNom());
        assertEquals(23, personneModifee.getAge());

        verify(personneRepository, times(1)).findById(id);
        verify(personneRepository, times(1)).save(personneAModifier);
    }
}
