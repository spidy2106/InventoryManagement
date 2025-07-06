import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service'
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'] // reuse same styles
})
export class RegisterComponent {
  name = '';
  phone = '';
  email = '';
  password = '';
  message = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    if (!this.name || !this.phone || !this.email || !this.password) {
      this.message = 'All fields are required';
      return;
    }

    const userData = {
      name: this.name,
      phone: this.phone,
      email: this.email,
      password: this.password
    };

    this.authService.register(userData).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: () => {
        this.message = 'Registration failed. Try again.';
      }
    });
  }
}

