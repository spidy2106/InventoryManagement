import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../shared/services/auth.service';
import { LoginResponse } from '../../shared/models/login-response.model';
@Component({
  selector: 'app-login',
  standalone: true,
  styleUrl: './login.component.css',
  imports: [NgIf, FormsModule],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
  if (!this.email || !this.password) {
    this.message = 'Please enter email and password';
    return;
  }

  const credentials = {
    email: this.email,
    password: this.password
  };

  this.authService.login(credentials).subscribe({
    next: (res: LoginResponse) => {
      localStorage.setItem('token', res.token);

      const role = res.role;
      if (role === 'ADMIN') {
        this.router.navigate(['/admin']);
      } else if (role === 'USER') {
        this.router.navigate(['/user']);
      } else {
        this.message = 'Unknown role';
      }
    },
    error: () => {
      this.message = 'Invalid credentials';
    }
  });
  }
}
