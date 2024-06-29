import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurServiceService {

  public url:string = "http://localhost:8080/";

  constructor(private httpClient: HttpClient) { }

  public register= (user: Utilisateur):Observable<Utilisateur>=>{

    const data = JSON.stringify(user);
    const sendUrl = this.url + "service/user/register";
    return this.httpClient.post<Utilisateur>(sendUrl,data,{headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  public login(user: Utilisateur): Observable<Utilisateur> {
    const data = JSON.stringify(user);
    const sendUrl = this.url + "service/user/login";
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.httpClient.post<Utilisateur>(sendUrl, data, { headers }).pipe(
      tap(res => {
        if (res.error && res.error.startsWith("Success")) {
          
        }
      }),
      catchError(error => {
        console.error('Login error', error);
        return throwError(error);
      })
    );
  }

}
