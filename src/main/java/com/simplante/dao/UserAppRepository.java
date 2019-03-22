package com.simplante.dao;

import com.simplante.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
