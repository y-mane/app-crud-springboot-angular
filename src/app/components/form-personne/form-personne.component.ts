import {Component, OnInit, Input} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, NgForm, Validators} from '@angular/forms';
import {Personne} from 'src/app/personne';
import {PersonneService} from 'src/app/personne.service';
import { MessageService } from 'primeng/api';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {Router} from "@angular/router";
import {Departement} from "../../departement";
import {DepartementService} from "../../departement.service";

interface AutoCompleteCompleteEvent {
  originalEvent: Event;
  query: string;
}


@Component({
  selector: 'app-form-personne',
  templateUrl: './form-personne.component.html',
  styleUrls: ['./form-personne.component.css'],
  providers: [ MessageService,ReactiveFormsModule,FormsModule ],
})
export class FormPersonneComponent implements OnInit {
  public departements: Departement[];
  visible: boolean = false;
  value: string;
  @Input() personne: Personne;
  public type_form = "";

  public personneForm: FormGroup;
  private router: Router;

  constructor(
    private fb: FormBuilder,
    private personnesService: PersonneService,
    private departementService: DepartementService,
    private messageService: MessageService,
  ) {
  }

  ngOnInit(): void {
    console.log('On Init');
    this.personneForm = this.fb.group({
      id: [],
      nom: ["", [Validators.required,Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*/)]],
      prenom: ["", [Validators.required,Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*/)]],
      age: ['', [Validators.required,Validators.pattern(/^(?:1[0-7][0-9]|150|[1-9][0-9]|[1-9])$/)]],
      departement: [null,[ Validators.required,]],
    });

    if (this.departementService){
      this.getDepartement();
    } else{
      console.log("fff");
    }
  }

  get nom(){
    return this.personneForm.controls['nom'];
  }
  get prenom(){
    return this.personneForm.controls['prenom'];
  }
  get age(){
    return this.personneForm.controls['age'];
  }
  get departement(){
    return this.personneForm.controls['departement'];
  }
  public getSelectedPersonne() {
    if (this.personne) {
      this.type_form = "Formulaire de modification";
      return this.afficherPersonne(this.personne);
    } else {
      this.type_form = "Formulaire d'Ajout de Personne";
      return this.afficherPersonne(new Personne());
    }
  }

  public afficherPersonne(personne: Personne) {
    this.personne = personne;
    this.personneForm.patchValue({
      id: this.personne['id'],
      nom: this.personne['nom'],
      prenom: this.personne['prenom'],
      age: this.personne['age'],
      departement: this.personne['departement'],

    });
  }

  public enregistrer(): void {
    console.log("le contenu du formulaire",this.personneForm.value);
    if (this.personneForm.valid && this.personne.id != null) {
      this.personnesService.modifierPersonne(this.personneForm.value, this.personne.id).subscribe({
        next: () => {
          this.enregistrementTerminer();
          this.messageService.add({ severity: 'info', summary: 'Confirmé', detail: 'Personne modifiée avec succès !' });
        }
      });
    } else {
      this.personnesService.ajouterPersonne(this.personneForm.value).subscribe({
        next: () => {
          this.enregistrementTerminer();
          this.messageService.add({ severity: 'info', summary: 'Confirmé', detail: 'Personne enregistrée avec succès !' });
        }
      });
    }
    this.visible = false;
  }

  public enregistrementTerminer(): void {
    this.personneForm.reset();
    this.router.navigate(['/personne'])
  }

  fermer() {
    this.visible = false;
  }

  showDialog() {
    this.visible = true;
    this.getSelectedPersonne();
  }


  getDepartement() {
    return this.departementService.getDepartement().subscribe({
      next: (data) => this.departements = data
    });
  }

  filteredDepartments: Departement[] | undefined;

  filterDepartement(event: AutoCompleteCompleteEvent) {
    let filtered: any[] = [];
    let query = event.query;

    for (let i = 0; i < (this.departements as Departement[]).length; i++) {
      let departement = (this.departements as Departement[])[i];
      console.log(departement);
      if (departement.designation.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(departement);
      }
    }

    this.filteredDepartments = filtered;
    console.log(this.filteredDepartments);
  }

}
