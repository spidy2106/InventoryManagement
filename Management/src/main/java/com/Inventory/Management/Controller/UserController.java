package com.Inventory.Management.Controller;

import com.Inventory.Management.DTO.CreateUserRequest;
import com.Inventory.Management.DTO.LoginResponse;
import com.Inventory.Management.DTO.OtpVerifyRequest;
import com.Inventory.Management.DTO.UserDTO;
import com.Inventory.Management.Entity.User;
import com.Inventory.Management.Service.UserService;
import com.Inventory.Management.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
       UserDTO createdUser = userService.createUser(request);
       return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
      return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}


