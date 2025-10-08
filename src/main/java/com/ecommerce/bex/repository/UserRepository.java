package com.ecommerce.bex.repository;

import com.ecommerce.bex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userInformation.username = :username")
    Optional<User> findByUsername(String username);
}
