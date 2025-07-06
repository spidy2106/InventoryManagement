import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component'; // Adjust path as per your structure
import { RegisterComponent } from './auth/register/register.component';
import { OtpComponent } from './auth/otp/otp.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';


export const routes: Routes = [
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'user', component: UserDashboardComponent },
  { path: 'otp', component: OtpComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'otp', component: OtpComponent },
  { path: '**', redirectTo: 'login' },
  { path: 'login',loadComponent: () =>import('./auth/login/login.component').then((m) => m.LoginComponent),
}

];
