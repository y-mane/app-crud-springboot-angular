import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {catchError, Observable} from 'rxjs';
import {Personne} from "./personne";
import {Departement} from "./departement";

@Injectable({
  providedIn: 'root'
})


export class DepartementService{
  readonly API_URL = "http://localhost:8080/api"
  readonly ENDPOINT_DEPARTEMENTS = "/departements"

  constructor(private httpclient: HttpClient) {}
  public getDepartement():Observable<Departement[]>{
    return this.httpclient.get<Departement[]>(this.API_URL+this.ENDPOINT_DEPARTEMENTS);
  }
}
