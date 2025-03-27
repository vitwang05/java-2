package com.java2.food.repository;


import java.util.Optional;

import com.java2.food.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);
}
