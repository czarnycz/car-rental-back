package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.ApplicationUserRole;

import java.util.Optional;

public interface ApplicationUserRoleRepository {
    boolean existsByName(String name);
    Optional<ApplicationUserRole> findByName(String name);
}
