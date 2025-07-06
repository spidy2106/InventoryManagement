package com.Inventory.Management.Repository;

import com.Inventory.Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmailId(String emailId);

    boolean existsByEmailId(String emailId);

}

