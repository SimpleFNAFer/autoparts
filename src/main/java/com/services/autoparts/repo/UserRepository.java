package com.services.autoparts.repo;

import com.services.autoparts.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String login);
    Boolean existsByUsername(String username);
}
