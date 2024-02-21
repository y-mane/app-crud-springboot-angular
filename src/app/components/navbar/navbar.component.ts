import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  items:MenuItem[] = [];
  public title = "TP-FRONT";

  ngOnInit(): void {
      this.items = [
        {
          label:'Acceuil',
          icon:'pi pi-fw pi-home',
          routerLink:'/'
        },
        {
          label:'Personnes',
          icon:'pi pi-fw pi-user',
          routerLink:'/personne'
        },
      ];
  }

}
