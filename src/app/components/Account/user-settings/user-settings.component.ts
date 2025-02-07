import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Utilisateur } from '../../../models/utilisateur';
import { UtilisateurServiceService } from '../../../service/user/utilisateur-service.service';
import { Router } from '@angular/router';
import { SideBarComponent } from '../../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../../partials/footer/footer.component';
import { UploadFileService } from '../../../service/upload-file.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-user-settings',
  standalone: true,
  imports: [RouterModule,FormsModule, SideBarComponent,NavBarComponent,FooterComponent,CommonModule],
  templateUrl: './user-settings.component.html',
  styleUrl: './user-settings.component.css'
})
export class UserSettingsComponent {

  user: Utilisateur;
  validate=false
  poolFile: { name: string, file: File }[] = [];

  constructor( private userService: UtilisateurServiceService, private uplaodService:UploadFileService, private router:Router){
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
    } else {
      this.user = new Utilisateur();
    }
  }

  onFileSelected(event: any, fileType: string) {
    const file: File = event.target.files[0];
    const existingFileIndex = this.poolFile.findIndex(f => f.name === fileType);
    

    if (existingFileIndex > -1) {
      this.poolFile[existingFileIndex].file = file;
    } else {
      this.poolFile.push({ name: fileType, file });
    }
    console.log(fileType)
  }

  uploadFiles(){
    
    var formData: FormData = new FormData();
    this.poolFile.forEach(fileObj => {
      if (fileObj.name==="nationalite"){
        this.user.nationalite=`${this.user.email}_${Date.now()}_${fileObj.file.name}`
      }else if(fileObj.name==="carte_identite"){
        this.user.carte_identite= `${this.user.email}_${Date.now()}_${fileObj.file.name}`
      }else if(fileObj.name==="residence"){
        this.user.certificat_residence= `${this.user.email}_${Date.now()}_${fileObj.file.name}`
      }
      const newFileName = `${this.user.email}_${Date.now()}_${fileObj.file.name}`;
      //const newFileName = `${this.user.email}_${Date.now()}`;
      const renamedFile = new File([fileObj.file], newFileName, { type: fileObj.file.type });
      formData.append(fileObj.name, renamedFile, renamedFile.name);
      this.uplaodService.uploadFile(formData)
      formData = new FormData();
    });

    /*return this.httpClient.post('/api/upload', formData, {
      headers: new HttpHeaders({
        'Content-Type': 'multipart/form-data'
      })
    });*/
  }

  update_user(){
    
    console.log("yes");
    
    this.uploadFiles();
    this.userService.register(this.user).subscribe(
      result=>{
        this.user=result
        console.log('Modification réussi ! Redirection vers la page de connexion...');
        if (this.user.error?.startsWith("Success")){
          console.log(this.user.nom)
          localStorage.setItem("user",JSON.stringify( this.user))
          this.validate=true
          window.scrollTo({ top: 10, behavior: 'smooth' });

        }
        
      
      
      // Redirige vers la page de connexion après l'enregistrement
      },
      (error) => {
        console.error('Erreur lors de l\'enregistrement : ', error);
        // Gérer l'erreur si nécessaire
      }
    )
  };
 
  download_file(fileType: string): Observable<any> {
    var fileName:string | undefined; 
    if (fileType==="residence"){
      fileName= this.user.certificat_residence;
    }else if (fileType==="nationalite"){
      fileName= this.user.nationalite;
    }else if (fileType==="carte_identite"){
      fileName= this.user.carte_identite;
    }else if (fileType==="profile"){
      fileName= this.user.profile;
    }
    alert(fileName);
    return this.uplaodService.downloadFile(fileName, fileType).pipe(
      tap((result: HttpResponse<Blob>) => {
        const contentDisposition = result.headers.get('Content-Disposition');
        let fileNameFromUrl = fileName; // Utiliser le nom d'utilisateur passé en paramètre

        if (contentDisposition) {
          const matches = contentDisposition.match(/filename="(.+)"/);
          if (matches && matches[1]) {
            fileNameFromUrl = matches[1];
          }
        }

        if (fileNameFromUrl) {
          const contentType = result.headers.get('Content-Type') || 'application/octet-stream';
          const blob = result.body ? new Blob([result.body], { type: contentType }) : new Blob([], { type: contentType });

          const link = document.createElement('a');
          link.href = window.URL.createObjectURL(blob);
          link.download = fileNameFromUrl;

          link.click();
          window.URL.revokeObjectURL(link.href);
          link.remove();
        }
      })
    );
  }

}

  
        
  






