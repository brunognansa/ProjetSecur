import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../../models/utilisateur';
import { UtilisateurServiceService } from '../../../service/user/utilisateur-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user: Utilisateur= new Utilisateur();
  user2:string | undefined ="";

  constructor( private userService: UtilisateurServiceService, private router:Router){}

  public login(){
    this.userService.login(this.user).subscribe(
      result=>{
        this.user=result
        this.user2=this.user.nom;
       
        if (this.user.error?.startsWith("Success")){
          localStorage.setItem("user",JSON.stringify( this.user))
        }
      // Redirige vers la page de connexion après l'enregistrement
      },
      (error) => {
        console.error('Erreur lors de l\'enregistrement : ', error);
        // Gérer l'erreur si nécessaire
      }
    )
  };
}
