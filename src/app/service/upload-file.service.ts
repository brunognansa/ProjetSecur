import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Utilisateur } from '../models/utilisateur';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private uploadUrl:string = "http://localhost:8080/service/upload"; // Remplacez par votre URL d'upload

  constructor(private http: HttpClient) { }

  public uploadFile(formData: FormData) {
    
    
    const url = this.uploadUrl+"/file";
    console.log(url);
    const upload$ = this.http.post("http://localhost:8080/service/upload/file", formData);

    upload$.subscribe();
    //return this.http.post(url, formData)
  }
  downloadFile(user: string | undefined, fileType: string): Observable<HttpResponse<Blob>> {
    if (!user) {
      throw new Error('User is undefined');
    }
    const url = `http://localhost:8080/service/upload/download/download?fileName=${user}&fileType=${fileType}`;
    return this.http.get(url, { responseType: 'blob', observe: 'response' });
  }


}
