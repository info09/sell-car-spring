package com.coding.repository;

import com.coding.entity.User;
import com.coding.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

    Optional<User> findFirstByEmail(String email);
}
