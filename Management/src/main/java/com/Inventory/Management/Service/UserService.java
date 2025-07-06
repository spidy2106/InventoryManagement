package com.Inventory.Management.Service;

import com.Inventory.Management.DTO.CreateUserRequest;
import com.Inventory.Management.DTO.LoginResponse;
import com.Inventory.Management.DTO.UserDTO;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO createUser(CreateUserRequest request);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);

}

