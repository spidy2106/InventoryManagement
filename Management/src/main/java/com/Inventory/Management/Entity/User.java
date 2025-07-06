package com.Inventory.Management.Entity;

import com.Inventory.Management.ENUM.Roles;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    private String phoneNumber;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING) // Store enum as string in DB
    private Roles role;

    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //setter


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserPassword(){
        return userPassword;
    }

    //Getters
    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Roles getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
