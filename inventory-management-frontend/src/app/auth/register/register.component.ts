import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Router,RouterModule} from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,
})
export class RegisterComponent implements OnInit {
  roles: string[] = [];
  selectedRole: string = '';
  
  // Other form fields
  email: string = '';
  password: string = '';
  phoneNumber: string = '';
  userName: string = '';
  message: string = '';


  constructor(private http: HttpClient, private router: Router) {}

  goToLogin() {
    this.router.navigate(['/login']);
  }

  ngOnInit(): void {
    this.http.get<string[]>('http://localhost:8080/api/auth/roles').subscribe({
      next: (data) => this.roles = data,
      error: () => this.message = 'Failed to load roles'
    });
  }

  register() {
    const payload = {
      emailId: this.email,
      password: this.password,
      phoneNumber: this.phoneNumber,
      userName: this.userName,
      role: this.selectedRole
    };

    this.http.post('http://localhost:8080/api/auth/signup', payload).subscribe({
      next: () => this.message = 'Registration successful',
      error: () => this.message = 'Registration failed'
    });
  }
}
