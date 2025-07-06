import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';   
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-otp',
  standalone: true,
  templateUrl: './otp.component.html',
  styleUrls: ['../login/login.component.css'],
  imports: [CommonModule, FormsModule]
})

export class OtpComponent {
  email = '';
  otp = '';
  otpSent = false;
  message = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  sendOtp() {
    if (!this.email) {
      this.message = 'Email is required';
      return;
    }

 this.authService.sendOtp(this.email).subscribe({
  next: (res) => {

    this.otpSent = true;
    this.message = res.message;
  },
  error: (err) => {
    this.message = 'Failed to send OTP';
  }
});



  }

  verifyOtp() {
    if (!this.otp) {
      this.message = 'Please enter OTP';
      return;
    }

    const payload = { email: this.email, otp: this.otp };

    this.authService.verifyOtp(payload).subscribe({
      next: (res: any) => {
        localStorage.setItem('token', res.token);
        if (res.role === 'ADMIN') this.router.navigate(['/admin']);
        else if (res.role === 'USER') this.router.navigate(['/user']);
        else this.message = 'Unknown role';
      },
      error: () => {
        this.message = 'OTP verification failed';
      }
    });
  }
}
