package com.Inventory.Management.ServiceImpl;

import com.Inventory.Management.DTO.CreateUserRequest;
import com.Inventory.Management.DTO.UserDTO;
import com.Inventory.Management.ENUM.Roles;
import com.Inventory.Management.Entity.User;
import com.Inventory.Management.Exception.UserNotFoundException;
import com.Inventory.Management.OTP.EmailService;
import com.Inventory.Management.Repository.OtpTokenRepo;
import com.Inventory.Management.Repository.UserRepo;
import com.Inventory.Management.Security.JwtUtil;
import com.Inventory.Management.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpTokenRepo otpTokenRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDTO getUserById(Long userId) {

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with ID "+userId));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }


    @Override
    public UserDTO createUser(CreateUserRequest request) {

        UserDTO userDTO = request.getUser();
        String password = request.getUserPassword();

        User user = modelMapper.map(userDTO, User.class);
        user.setUserPassword(password);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        if (user.getRole() == null) {
            user.setRole(Roles.ADMIN);
        }


        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser,UserDTO.class);


    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        userRepo.delete(user);
    }



}
