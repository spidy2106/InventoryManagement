import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component'; // Adjust path as per your structure
import { RegisterComponent } from './auth/register/register.component';
import { OtpComponent } from './auth/otp/otp.component';


export const routes: Routes = [
  { path: 'otp', component: OtpComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'otp', component: OtpComponent },
  { path: '**', redirectTo: 'login' }
];
