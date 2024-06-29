import { Component } from '@angular/core';
import { SideBarComponent } from '../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../partials/footer/footer.component';
import { AsideComponent } from '../partials/aside/aside.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { UtilisateurServiceService } from '../../service/user/utilisateur-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    SideBarComponent,
    NavBarComponent,
    FooterComponent,
    AsideComponent,
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
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
