package com.Inventory.Management.Service;

import com.Inventory.Management.DTO.LoginRequest;
import com.Inventory.Management.DTO.LoginResponse;
import com.Inventory.Management.DTO.SignupRequest;
import com.Inventory.Management.DTO.UserDTO;

public interface AuthService {
    UserDTO signup(SignupRequest request);
    LoginResponse login(LoginRequest request);
    String sendOtpViaEmail(String emailId);
    LoginResponse verifyOtp(String emailId, String otp);
}

