package com.simplante.dao;

import com.simplante.model.RoleApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAppRepository extends JpaRepository<RoleApp,Long> {
    RoleApp findByRole(String role);
}
