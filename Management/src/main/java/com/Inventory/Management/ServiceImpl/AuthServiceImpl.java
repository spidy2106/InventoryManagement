package com.Inventory.Management.ServiceImpl;

import com.Inventory.Management.DTO.*;
import com.Inventory.Management.ENUM.Roles;
import com.Inventory.Management.Entity.OtpToken;
import com.Inventory.Management.Entity.User;
import com.Inventory.Management.Exception.EmailAlreadyExistsException;
import com.Inventory.Management.Exception.UserNotFoundException;
import com.Inventory.Management.OTP.EmailService;
import com.Inventory.Management.Repository.OtpTokenRepo;
import com.Inventory.Management.Repository.UserRepo;
import com.Inventory.Management.Security.JwtUtil;
import com.Inventory.Management.Service.AuthService;
import com.Inventory.Management.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired private OtpTokenRepo otpTokenRepo;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private EmailService emailService;
    @Autowired private ModelMapper modelMapper;
    @Autowired private UserService userService;



    @Override
    public UserDTO signup(SignupRequest request) {
        String email = request.getEmailId();

        if (userRepo.findByEmailId(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + email);
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailId(email);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUserPassword(request.getPassword()); // No encryption yet
        Roles role = Roles.valueOf(request.getRole()); // safely map from string
        user.setRole(role);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepo.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepo.findByEmailId(request.getEmailId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getUserPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String jwt = jwtUtil.generateToken(user.getEmailId(), user.getRole().name());

        LoginResponse res = new LoginResponse();
        res.setMessage("Login successful");
        res.setToken(jwt);
        res.setRole(user.getRole().name());
        return res;
    }

    @Override
    public String sendOtpViaEmail(String emailId) {
        User user = userRepo.findByEmailId(emailId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        OtpToken token = new OtpToken();
        token.setEmail(emailId);
        token.setOtp(otp);
        token.setGeneratedAt(LocalDateTime.now());
        otpTokenRepo.save(token);

        emailService.sendOtp(emailId, otp);
        return "OTP sent to email";
    }

    @Override
    @Transactional
    public LoginResponse verifyOtp(String emailId, String otp) {
        OtpToken token = otpTokenRepo.findTopByEmailOrderByGeneratedAtDesc(emailId)
                .orElseThrow(() -> new IllegalArgumentException("No OTP found"));

        if (token.getGeneratedAt().plusMinutes(5).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP expired");
        }

        if (!token.getOtp().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        otpTokenRepo.deleteByEmail(emailId); // optional cleanup

        User user = userRepo.findByEmailId(emailId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String jwt = jwtUtil.generateToken(user.getEmailId(), user.getRole().name());

        LoginResponse response = new LoginResponse();
        response.setMessage("Login via OTP successful");
        response.setToken(jwt);
        response.setRole(user.getRole().name());
        return response;
    }
}
