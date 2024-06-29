import { Component } from '@angular/core';
import { Utilisateur } from '../../../models/utilisateur';
import { UtilisateurServiceService } from '../../../service/user/utilisateur-service.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormsModule,RouterModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {

  user: Utilisateur= new Utilisateur();

  constructor( private userService: UtilisateurServiceService, private router:Router){}

  public register(){
    this.user.role="ROLE_USER";
    console.log("yes");
    this.userService.register(this.user).subscribe(
      result=>{
        this.user=result
        console.log('Enregistrement réussi ! Redirection vers la page de connexion...');
        if (this.user.error?.startsWith("Success")){

          this.router.navigate(['/login']); 

        }
        
      
      
      // Redirige vers la page de connexion après l'enregistrement
      },
      (error) => {
        console.error('Erreur lors de l\'enregistrement : ', error);
        // Gérer l'erreur si nécessaire
      }
    )
  };

  public login(){
    this.userService.login(this.user).subscribe(
      result=>{
        this.user=result
        console.log('Enregistrement réussi ! Redirection vers la page de connexion...');
        if (this.user.error?.startsWith("Success")){

          this.router.navigate(['/login']); 

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
