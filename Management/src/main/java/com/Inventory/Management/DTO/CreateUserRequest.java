package com.Inventory.Management.DTO;

public class CreateUserRequest {
    private UserDTO user;
    private String userPassword;

    public UserDTO getUser() {
        return user;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

