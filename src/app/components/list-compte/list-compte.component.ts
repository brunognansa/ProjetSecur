
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { Compte } from '../../models/compte';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { SideBarComponent } from '../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../partials/footer/footer.component';
import { AsideComponent } from '../partials/aside/aside.component';
import { CompteService } from '../../service/compte/compte.service';

@Component({
  selector: 'app-list-compte',
  standalone:true,
  imports: [
    SideBarComponent,
    NavBarComponent,
    FooterComponent,
    AsideComponent,
    FormsModule,
    CommonModule
  ],
  templateUrl: './list-compte.component.html',
  styleUrl: './list-compte.component.css'
})
export class ListCompteComponent {

  
  user: Utilisateur;
  compte: Compte[]=[];

  constructor( private compteService: CompteService , private router:Router){
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
      compteService.get_compte(this.user).subscribe(
        result=>{
          this.compte=result

        // Redirige vers la page de connexion après l'enregistrement
        },
        (error) => {
          console.error('Erreur lors de l\'enregistrement : ', error);
          // Gérer l'erreur si nécessaire
        }
      )
  
    }

    else {
      this.user = new Utilisateur();
    }
  }

}



