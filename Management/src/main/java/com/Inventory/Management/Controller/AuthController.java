package com.Inventory.Management.Controller;

import com.Inventory.Management.DTO.*;
import com.Inventory.Management.ENUM.Roles;
import com.Inventory.Management.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/roles")
    public List<String> getAvailableRoles() {
        return Arrays.stream(Roles.values())
                .map(Enum::name)
                .toList();
    }


    @PostMapping("/send-otp-email")
    public ResponseEntity<Map<String, String>> sendOtpToEmail(@RequestParam String emailId) {
        String message = authService.sendOtpViaEmail(emailId);
        Map<String, String> res = new HashMap<>();
        res.put("message", message);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<LoginResponse> verifyOtp(@RequestBody OtpVerifyRequest request) {
        LoginResponse response = authService.verifyOtp(request.getEmailId(), request.getOtp());
        return ResponseEntity.ok(response);
    }
}
