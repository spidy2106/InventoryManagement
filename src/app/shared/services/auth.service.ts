import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login-response.model'; 

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  // Login user with email & password
  login(credentials: { email: string; password: string }): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, credentials);
  }

  // Register new user
  register(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, userData);
  }

  // Send OTP to user
  sendOtp(email: string): Observable<any> {
  return this.http.post(
    `${this.baseUrl}/send-otp-email?emailId=${email}`,
    null 
  );
}

  // Verify OTP
  verifyOtp(payload: { email: string; otp: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify-otp`, payload);
  }
}
