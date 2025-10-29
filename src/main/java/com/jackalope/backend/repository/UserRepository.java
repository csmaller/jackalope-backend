package com.jackalope.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jackalope.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from user where first_name= ?1", nativeQuery = true)
    List<User> findAllUserByFirstName(String firstName);
    
    Optional<User> findByEmail(String email);
}
