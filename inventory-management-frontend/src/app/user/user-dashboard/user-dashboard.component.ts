import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {
  userName: string = ''; 

  constructor(private router: Router) {}

  goToProfile() {
    this.router.navigate(['/user/profile']); 
  }

  logout() {
    localStorage.clear(); 
    this.router.navigate(['/login']);
  }
}
