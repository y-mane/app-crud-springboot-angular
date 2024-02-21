import {Departement} from "./departement";

export class Personne {
    id:number;
    nom:String;
    prenom:String;
    age:Number | '';
    departement:Departement

    /*constructor(id:number,nom:String,prenom:String,age:number){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }*/
    constructor(){}

}
