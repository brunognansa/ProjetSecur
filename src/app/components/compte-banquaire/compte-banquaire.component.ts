import { Component } from '@angular/core';
import { SideBarComponent } from '../partials/side-bar/side-bar.component';
import { NavBarComponent } from '../partials/nav-bar/nav-bar.component';
import { FooterComponent } from '../partials/footer/footer.component';
import { AsideComponent } from '../partials/aside/aside.component';

@Component({
  selector: 'app-compte-banquaire',
  standalone: true,
  imports: [
    SideBarComponent,
    NavBarComponent,
    FooterComponent,
    AsideComponent,
  ],
  templateUrl: './compte-banquaire.component.html',
  styleUrl: './compte-banquaire.component.css'
})
export class CompteBanquaireComponent {

}
