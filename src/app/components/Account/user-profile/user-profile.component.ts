import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../../models/utilisateur';
import { UtilisateurServiceService } from '../../../service/user/utilisateur-service.service';
import { Router } from '@angular/router';
import { SideBarComponent } from '../../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../../partials/footer/footer.component';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [RouterModule,FormsModule, SideBarComponent,NavBarComponent,FooterComponent],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  user: Utilisateur;

  constructor( private userService: UtilisateurServiceService, private router:Router){
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
    } else {
      this.user = new Utilisateur();
    }
  }

}


