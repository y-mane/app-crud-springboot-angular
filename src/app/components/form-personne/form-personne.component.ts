import {Component, OnInit, Input, ChangeDetectorRef} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, NgForm, Validators} from '@angular/forms';
import {Personne} from 'src/app/personne';
import {PersonneService} from 'src/app/personne.service';
import {ConfirmationService, MessageService} from 'primeng/api';
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
    providers: [MessageService, ReactiveFormsModule, FormsModule, ConfirmationService],
})
/**
 * Composant du formulaire de personne
 */
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
        private cdRef: ChangeDetectorRef
    ) {
    }

    /**
     * Initialise la page avec le formulaire vide,
     * la liste des départements et détecte les changements
     */
    ngOnInit(): void {
        this.personneForm = this.fb.group({
            id: [],
            nom: ["", [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*/)]],
            prenom: ["", [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*/)]],
            age: ['', [Validators.required, Validators.pattern(/^(?:1[0-7][0-9]|150|[1-9][0-9]|[1-9])$/)]],
            departement: [null, [Validators.required,]],
        });

        this.getDepartement();
        this.cdRef.detectChanges();
    }

    get nom() {
        return this.personneForm.controls['nom'];
    }

    /**
     * Recupére le prenom
     */
    get prenom() {
        return this.personneForm.controls['prenom'];
    }

    /**
     * Recupère l'age
     */
    get age() {
        return this.personneForm.controls['age'];
    }

    /**
     * Recupère le département
     */
    get departement() {
        return this.personneForm.controls['departement'];
    }

    /**
     * Recupère la personne selectionnée quand on choisit une action
     */
    public getSelectedPersonne() {
        if (this.personne) {
            this.type_form = "Formulaire de modification";
            return this.afficherPersonne(this.personne);
        } else {
            this.type_form = "Formulaire d'Ajout de Personne";
            return this.afficherPersonne(new Personne());
        }
    }

    /**
     * Affiche les infos de la personne selectionée dans le formulaire
     * @param personne la personne dont on veut modifier les infos
     */
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

    /**
     * Enregistre les modifications dans le formulaire
     * dans le cas de l'ajout ou la modification
     */
    public enregistrer(): void {
        if (this.personneForm.valid && this.personne.id != null) {
            this.personnesService.modifierPersonne(this.personneForm.value, this.personne.id).subscribe({
                next: () => {
                    this.enregistrementTerminer();
                    this.messageService.add({severity: 'info', summary: 'Confirmé', detail: 'Personne modifiée avec succès !'});
                }
            });
        } else {
            this.personnesService.ajouterPersonne(this.personneForm.value).subscribe({
                next: () => {
                    this.enregistrementTerminer();
                    this.messageService.add({severity: 'info', summary: 'Confirmé', detail: 'Personne enregistrée avec succès !'});
                }
            });
        }
        this.visible = false;
    }

    /**
     * Rénitialise le formulaire après enregistrement
     */
    public enregistrementTerminer(): void {
        this.personneForm.reset();
        this.router.navigate(['/personne'])
    }

    /**
     * ferme le popup du formulaire
     */
    fermer() {
        this.visible = false;
    }

    /**
     * Ouvre le popup du formulaire et appelle
     * la fonction getSelectedPersonne()
     */
    showDialog() {
        this.visible = true;
        this.getSelectedPersonne();
    }


    /**
     * Ramène tous les départements
     */
    getDepartement() {
        return this.departementService.getDepartement().subscribe({
            next: (data) => this.departements = data
        });
    }

    filteredDepartments: Departement[] | undefined;

    /**
     * Filtre les départements par caractère de désignation entré au clavier
     *
     * @param event l'évènement qui prend les modifications du clavier
     */
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
    }

}
