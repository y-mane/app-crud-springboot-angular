import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import { PersonneService } from 'src/app/personne.service';
import { MessageService } from 'primeng/api';
import { of } from 'rxjs';
import { DepartementService } from "../../departement.service";


import {NO_ERRORS_SCHEMA} from "@angular/core";

import { FormPersonneComponent } from './form-personne.component';
import {InputTextModule} from "primeng/inputtext";
import {DropdownModule} from "primeng/dropdown";
import {MenuModule} from "primeng/menu";
import {TabMenuModule} from "primeng/tabmenu";
import {HttpClientModule} from "@angular/common/http";
import {ButtonModule} from "primeng/button";
import {AutoCompleteModule} from "primeng/autocomplete";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ToastModule} from "primeng/toast";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DialogModule} from "primeng/dialog";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Personne} from "../../personne";


describe('FormPersonneComponent', () => {
  let component: FormPersonneComponent;
  let fixture: ComponentFixture<FormPersonneComponent>;
  let mockPersonneService: PersonneService;
  let mockMessageService: MessageService;
  let mockDepartementService: DepartementService;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      declarations: [ FormPersonneComponent ],
      schemas : [NO_ERRORS_SCHEMA ],
      providers: [
        PersonneService,
        MessageService,
        DepartementService,
      ],
      imports: [
        ButtonModule,
        MenuModule,
        TabMenuModule,
        HttpClientModule,
        DialogModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        FormsModule,
        InputTextModule,
        ToastModule,
        ConfirmDialogModule,
        AutoCompleteModule,
        DropdownModule,
      ],
    })
    .compileComponents();
    fixture = TestBed.createComponent(FormPersonneComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
    fixture.detectChanges();
    mockPersonneService = TestBed.inject(PersonneService);
    mockMessageService = TestBed.inject(MessageService);
    mockDepartementService = TestBed.inject(DepartementService);
  });

  /**
   * Test la création du composant
   *
   */
  it('devrait creer le composant', () => {
    expect(component).toBeTruthy();
  });

  /**
   * Test la fonction enregistrement() avec
   * la fonction modifierPersonne du service Personne
   */
  it("devrait modifier une personne ",  () =>  {
    //given
    component.personne = new Personne();
    component.personne.id = 2;
    component.personne.nom = 'John';
    component.personne.prenom = "Snow";
    component.personne.age = 65;
    component.personne.departement = {id: 1, code: "DP001", designation: "Informatique"};
    const formPersonne = {id: 2, nom: "Keita", prenom: "Ymane", age: 22, departement: {id: 1, code: "DP001", designation: "Informatique"}};
    component.personneForm.setValue(formPersonne);
    const mockResponse = {
      success: true,
      message: 'Personne modifiée avec succès',
    };

    spyOn(mockPersonneService,'modifierPersonne').and.returnValue(of(formPersonne));
    spyOn(component ,'enregistrementTerminer');
    spyOn(mockDepartementService,'getDepartement');
    spyOn(mockMessageService,'add');


    //when
    component.enregistrer();


    //then
    expect(mockPersonneService.modifierPersonne).toHaveBeenCalledWith(formPersonne,2);
    expect(component.visible).toBeFalse();
  });

  /**
   * Test la fonction enregistrer() avec
   * ajouterPersonne() du service Personne
   */
  it("devrait ajouter une personne ", () => {
    //given
    component.personne = {id: null, nom: "", prenom: "", age: '', departement: null};

    spyOn(mockPersonneService, "ajouterPersonne").and.returnValue(of(component.personne));
    spyOn(component ,'enregistrementTerminer');
    spyOn(mockDepartementService,'getDepartement');
    spyOn(mockMessageService,'add');

    //when
    component.enregistrer();

    //then
    expect(mockPersonneService.ajouterPersonne).toHaveBeenCalled();
    expect(mockPersonneService.ajouterPersonne).toHaveBeenCalledWith(component.personne);
    //expect(mockMessageService.add).toHaveBeenCalled();
    expect(component.visible).toBeFalse();

  });

  });
