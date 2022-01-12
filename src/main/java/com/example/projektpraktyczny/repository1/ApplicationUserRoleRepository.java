package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {
    boolean existsByName(String name);
    Optional<ApplicationUserRole> findByName(String name);
}
