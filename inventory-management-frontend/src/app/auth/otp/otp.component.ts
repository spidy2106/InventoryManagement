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

  const payload = {
    emailId: this.email.trim(),
    otp: this.otp.trim()
  };

  this.authService.verifyOtp(payload).subscribe({
    next: (res: any) => {
      if (res.token) {
        localStorage.setItem('token', res.token);

        switch (res.role) {
          case 'ADMIN':
            this.router.navigate(['/admin']);
            break;
          case 'USER':
            this.router.navigate(['/user']);
            break;
          default:
            this.message = 'Unknown role. Contact admin.';
        }
      } else {
        this.message = 'Invalid server response';
      }
    },
    error: () => {
      this.message = 'OTP verification failed';
    }
  });
}
}
