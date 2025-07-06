package com.Inventory.Management.Repository;

import com.Inventory.Management.Entity.OtpToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpTokenRepo extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findTopByEmailOrderByGeneratedAtDesc(String email);
    void deleteByEmail(String email);
}
