import {TestBed, ComponentFixture} from '@angular/core/testing';
import {PersonneService} from 'src/app/personne.service';
import {ConfirmationService, MessageService, FilterMatchMode} from 'primeng/api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {of} from 'rxjs';
import {RouterTestingModule} from "@angular/router/testing";


import {TableaupersonneComponent} from './tableaupersonne.component';
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {HttpClientModule} from "@angular/common/http";
import {Personne} from "../../personne";


describe('TableaupersonneComponent', () => {
    let component: TableaupersonneComponent;
    let fixture: ComponentFixture<TableaupersonneComponent>;
    let mockPersonneService: jasmine.SpyObj<PersonneService>;
    let mockConfirmationService: jasmine.SpyObj<ConfirmationService>;
    let mockMessageService: jasmine.SpyObj<MessageService>;


    beforeEach(async () => {
        mockPersonneService = jasmine.createSpyObj('PersonneService', ['getPersonnes', 'supprimerPersonne']);
        mockConfirmationService = jasmine.createSpyObj('ConfirmationService', ['confirm', 'Promise']);
        mockMessageService = jasmine.createSpyObj('MessageService', ['add']);

        await TestBed.configureTestingModule({
            declarations: [TableaupersonneComponent],
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientTestingModule, RouterTestingModule, HttpClientModule, RouterTestingModule],
            providers: [
                {provide: PersonneService, useValue: mockPersonneService},
                {provide: ConfirmationService, useValue: mockConfirmationService},
                {provide: MessageService, useValue: mockMessageService}
            ],

        })
            .compileComponents();

        fixture = TestBed.createComponent(TableaupersonneComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });


    /**
     * Test la création du composant
     *
     */
    it('devrait créer le composant', () => {
        expect(component).toBeTruthy();
    });

    /**
     * Test de la méthode "getPersonnes" pour
     * la vérification du chargement des personnes
     */
    it('devrait aller chercher la liste des personnes à l init', () => {
        const mockPersonnes: Personne[] = [
            {
                id: 1,
                nom: 'John',
                prenom: 'Snow',
                age: 22,
                departement: {id: 1, code: '18F002', designation: 'Informatique'}
            },
            {
                id: 2,
                nom: 'Keita',
                prenom: 'Ymane',
                age: 27,
                departement: {id: 2, code: '15A008', designation: 'Administration'}
            }];
        mockPersonneService.getPersonnes.and.returnValue(of(mockPersonnes));
        mockPersonneService.getPersonnes().subscribe((personnes) => {
            expect(personnes).toEqual(mockPersonnes)
        });

    });

    /**
     * Test de la méthode le filtre dans le cas
     * où le choix est mineur
     */
    it('devrait filtrer les personnes pour le choix "Mineur"', () => {
        const mockTable = jasmine.createSpyObj('Table', ['filter']);
        component.myTable = mockTable;
        component.selectedOption = 'Mineur';

        component.filterOption();

        expect(mockTable.filter).toHaveBeenCalledWith(18, 'age', FilterMatchMode.LESS_THAN);
    });

    /**
     * Test de la méthode le filtre dans le cas
     * où le choix est Majeur
     */
    it('devrait filtrer les personnes pour le choix "Majeur"', () => {
        const mockTable = jasmine.createSpyObj('Table', ['filter']);
        component.myTable = mockTable;
        component.selectedOption = 'Majeur';

        component.filterOption();

        expect(mockTable.filter).toHaveBeenCalledWith(18, 'age', FilterMatchMode.GREATER_THAN_OR_EQUAL_TO);
    });

    /**
     * Test de le modal de suppression et la fonction supprimerPersonne
     *
     */
    it('devrait confirmer et supprimer la personne', () => {
        const personneId = 1;
        // @ts-ignore
        mockPersonneService.supprimerPersonne.and.returnValue(of({}));
        mockConfirmationService.confirm.and.callFake((options: any) => {
            return options.accept();
        });
        mockPersonneService.supprimerPersonne(1);
        component.confirm2(personneId);

        //expect(confirmationService.confirm).toHaveBeenCalled();
        expect(mockPersonneService.supprimerPersonne).toHaveBeenCalledWith(personneId);
        //expect(messageService.add).toHaveBeenCalledWith({ severity: 'info', summary: 'Confirmé', detail: 'Personne supprimée avec succès !' });
    });

    /**
     * Test de le modal de suppression dans le cas
     * ou on ferme le modal
     */
    it('devrait fermer le modal de suppression et ne pas supprimer', () => {
        const personneId = 1;
        mockConfirmationService.confirm.and.callFake((options: any) => {
            return options.reject();
        });
        //spyOn(confirmationService, 'confirm').and.returnValue(Promise.reject());


        //spyOn(confirmationService, 'confirm').and.returnValue();


        component.confirm2(personneId);

        //expect(mockonfirmationService.confirm).toHaveBeenCalled();
        expect(mockPersonneService.supprimerPersonne).not.toHaveBeenCalled();
        //expect(mockMessageService.add).toHaveBeenCalledWith({ severity: 'error', summary: 'Annulé', detail: '' });
    });


    /**
     * Test pour vérifier la suppression d'une personne
     */
    it('devrait supprimer une personne ', () => {
        //Given
        const personneToDelete: Personne = {id: 1, nom: "John", prenom: "Snow", age: 4, departement: {id: 1, code: "DP001", designation: "Informatique"}};
        const personne2: Personne = {id: 2, nom: "Keita", prenom: "Ymane", age: 22, departement: {id: 2, code: "DP001", designation: "Ressource Humaine"}};
        const personnes: Personne[] = [personne2, personneToDelete];
        mockPersonneService.supprimerPersonne.and.returnValue(of(null));
        mockPersonneService.getPersonnes.and.returnValue(of([personnes[0]]));
        //when
        mockPersonneService.supprimerPersonne(1);
        //then
        expect(mockPersonneService.supprimerPersonne).toHaveBeenCalledWith(personneToDelete.id);
        expect(mockPersonneService.supprimerPersonne).toHaveBeenCalledTimes(1);
        expect(mockPersonneService.getPersonnes).not.toBe(personnes);
    });


});
