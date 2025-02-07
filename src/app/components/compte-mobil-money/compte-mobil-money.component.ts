import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { Compte } from '../../models/compte';
import { UtilisateurServiceService } from '../../service/user/utilisateur-service.service';
import { Router } from '@angular/router';
import { SideBarComponent } from '../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../partials/footer/footer.component';
import { AsideComponent } from '../partials/aside/aside.component';
import { CompteService } from '../../service/compte/compte.service';


@Component({
  selector: 'app-compte-mobil-money',
  standalone: true,
  imports: [
    SideBarComponent,
    NavBarComponent,
    FooterComponent,
    AsideComponent,
    FormsModule,
  ],
  templateUrl: './compte-mobil-money.component.html',
  styleUrl: './compte-mobil-money.component.css'
})
export class CompteMobilMoneyComponent {
  user: Utilisateur;
  compte: Compte=new Compte();

  constructor( private compteService: CompteService , private router:Router){
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
      this.compte.user=this.user;
    } else {
      this.user = new Utilisateur();
      
    }
  }

  add_compte(){
    alert(this.compte.user)
    this.compte.nom_compte= "compte145"+this.compte.institution_financiere
    this.compteService.add_compte(this.compte).subscribe(
      result=>{
        if (this.user.error?.startsWith("Success")){

          this.router.navigate(['/list-compte']);

        }
        
      
      
      // Redirige vers la page de connexion après l'enregistrement
      },
      (error) => {
        console.error('Erreur lors de l\'enregistrement : ', error);
        // Gérer l'erreur si nécessaire
      }
    )

  }


}
