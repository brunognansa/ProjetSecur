import { Injectable } from '@angular/core';
import { Compte } from '../../models/compte';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompteService {
  public url:string = "http://localhost:8080/";

  constructor(private httpClient: HttpClient) { }

  public add_compte(user:Compte):Observable<Compte>{
    
      alert("yes");
      const data = JSON.stringify(user);
      const sendUrl = this.url + "service/compte/add-mobil-money";
      return this.httpClient.post<Compte>(sendUrl,data,{headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  public get_compte(user:Utilisateur): Observable<Compte[]> 
  {
    //const data = JSON.stringify(user);
    const sendUrl = this.url + "service/compte/get_compte";
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.httpClient.post<Compte[]>(sendUrl, user, { headers })
  }
}
