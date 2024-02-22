import {Component, ChangeDetectorRef, OnInit, ViewChild} from '@angular/core';

import {PersonneService} from 'src/app/personne.service';
import {DepartementService} from 'src/app/departement.service';
import {Personne} from 'src/app/personne';
import {ConfirmationService, MessageService, FilterMatchMode} from 'primeng/api';
import {Departement} from "../../departement";
import {Table} from "primeng/table";


@Component({
    selector: 'app-tableaupersonne',
    templateUrl: './tableaupersonne.component.html',
    styleUrls: ['./tableaupersonne.component.css'],
    providers: [ConfirmationService, MessageService, PersonneService]
})


export class TableaupersonneComponent implements OnInit {
    public title = "Liste Des Personnes";
    public personnes: Personne[];
    public departements: Departement[];
    plusPetit18: boolean = false;
    public personnesFiltre: Personne[];

    @ViewChild('myTable')
    myTable: Table;

    ageOptions = ['Mineur', 'Majeur'];
    selectedOption: string;


    constructor(
        private personneService: PersonneService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private departementService: DepartementService,
        private cdRef: ChangeDetectorRef
    ) {
    }


    confirm2(id: Number) {
        this.confirmationService.confirm({
            message: 'Voulez vous supprimer cette personne?',
            header: 'Confirmation de suppression',
            icon: 'pi pi-info-circle',
            acceptButtonStyleClass: "p-button-danger p-button-text",
            rejectButtonStyleClass: "p-button-text p-button-text",

            accept: () => {
                this.personneService.supprimerPersonne(id).subscribe({});
                this.messageService.add({severity: 'info', summary: 'Confirmé', detail: 'Personne supprimée avec succès !'});
                this.getPersonnes();
            },
            reject: () => {
                this.messageService.add({severity: 'error', summary: 'Annulé', detail: ''});
            }

        });
    }

    ngOnInit(): void {
        this.getPersonnes();
        this.cdRef.detectChanges();
    }

    getPersonnes() {
        return this.personneService.getPersonnes().subscribe({
            next: (data) => this.personnes = data
        });
    }

    filterOption() {
        this.selectedOption === "Mineur" ?
            this.myTable.filter(18, 'age', FilterMatchMode.LESS_THAN)
            :
            this.myTable.filter(18, 'age', FilterMatchMode.GREATER_THAN_OR_EQUAL_TO);
    }


}
