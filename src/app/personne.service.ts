import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {catchError, Observable} from 'rxjs';
import { Personne } from './personne';

@Injectable({
  providedIn: 'root'
})

export class PersonneService {
  readonly API_URL = "http://localhost:8080/api"

  readonly ENDPOINT_PERSONNES = "/personnes"

  readonly ENDPOINT_PERSONNE = "/personne/"
  readonly ENDPOINT_MODIFIER = "/modifier/"
  readonly ENDPOINT_SUPPRIMER = "/supprimer/"
  readonly ENDPOINT_AJOUTER = "/ajouter"

  constructor(private httpclient: HttpClient) {}

   public getPersonnes():Observable<Personne[]>{
     return this.httpclient.get<Personne[]>(this.API_URL+this.ENDPOINT_PERSONNES);
   }

   public getpersonne(id:Number):Observable<Personne>{
    return this.httpclient.get<Personne>(this.API_URL+this.ENDPOINT_PERSONNE+id);
   }

   public ajouterPersonne(personne:Personne){
    return this.httpclient.post<Personne>(this.API_URL+this.ENDPOINT_AJOUTER, personne);
   }
   public modifierPersonne(personne:Personne,id:number){
    return this.httpclient.put<Personne>(this.API_URL+this.ENDPOINT_MODIFIER+id,personne);
   }

   public supprimerPersonne(id:Number){
    return this.httpclient.delete<Personne>(this.API_URL+this.ENDPOINT_SUPPRIMER+id)
   }
}
