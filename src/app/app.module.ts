import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {FooterComponent} from './components/footer/footer.component';
import {MenubarModule} from 'primeng/menubar';
import {RouterModule} from "@angular/router";
import {TableaupersonneComponent} from './components/tableaupersonne/tableaupersonne.component';
import {TableModule} from 'primeng/table';
import {PersonneService} from './personne.service';
import {HttpClientModule} from '@angular/common/http';
import {ButtonModule} from 'primeng/button';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ToastModule} from 'primeng/toast';
import {ConfirmationService, MessageService} from 'primeng/api';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormPersonneComponent} from './components/form-personne/form-personne.component';
import {DialogModule} from 'primeng/dialog';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MessagesModule} from 'primeng/messages';
import {Personne} from './personne';
import {ActivatedRoute} from '@angular/router';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {DropdownModule} from "primeng/dropdown";


@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        FooterComponent,
        TableaupersonneComponent,
        FormPersonneComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MenubarModule,
        RouterModule.forRoot([
            {path: 'personne', component: TableaupersonneComponent},
        ]),
        TableModule,
        HttpClientModule,
        ButtonModule,
        ConfirmDialogModule,
        ToastModule,
        BrowserAnimationsModule,
        DialogModule,
        InputTextModule,
        FormsModule,
        MessagesModule,
        ReactiveFormsModule,
        AutoCompleteModule,
        DropdownModule,

    ],
    providers: [PersonneService, MessageService, ConfirmationService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
