import { Routes } from '@angular/router';
import { LoginComponent } from './components/sign-in/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { ForgotPasswordComponent } from './components/sign-in/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './components/sign-in/reset-password/reset-password.component';
import { RegistrationComponent } from './components/sign-up/registration/registration.component';
import { FrontendComponent } from './frontend/frontend.component';
import { CompteMobilMoneyComponent } from './components/compte-mobil-money/compte-mobil-money.component';
import { UserProfileComponent } from './components/Account/user-profile/user-profile.component';
import { UserSettingsComponent } from './components/Account/user-settings/user-settings.component';
import { ListCompteComponent } from './components/list-compte/list-compte.component';
import { ListTransactionComponent } from './components/list-transaction/list-transaction.component';
import { CompteBanquaireComponent } from './components/compte-banquaire/compte-banquaire.component';
import { AccueilComponent } from './components/PageAccueil/accueil/accueil.component';
import { WhyMoneyBridgeComponent } from './components/PageAccueil/why-money-bridge/why-money-bridge.component';


export const routes: Routes = [

    { path: '', redirectTo: 'accueil', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'admin', component: AdminComponent },
    { path: 'forgot-password', component: ForgotPasswordComponent },
    { path: 'reset-password', component: ResetPasswordComponent },
    { path: 'registration', component: RegistrationComponent },
    { path: 'front', component: FrontendComponent },
    { path: 'add-mobile-money', component: CompteMobilMoneyComponent},
    {path: 'add-compte-banquaire', component: CompteBanquaireComponent},
    { path: 'user-profile', component: UserProfileComponent },
    { path: 'user-settings', component: UserSettingsComponent },
    { path:'list-compte', component: ListCompteComponent},
    { path:'list-transaction', component: ListTransactionComponent},
    { path:'', component: AccueilComponent},
    { path:'why-money-bridge', component: WhyMoneyBridgeComponent},
    

];
