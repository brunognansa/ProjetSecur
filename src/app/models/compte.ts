import { Utilisateur } from "./utilisateur";

export class Compte {
    id?:number;
    numero_compte?: string;
    nom_compte?: string;
    error?: string;
    user?: Utilisateur;
    institution_financiere?:string;
    date:string="---";
    solde:string="---";


}