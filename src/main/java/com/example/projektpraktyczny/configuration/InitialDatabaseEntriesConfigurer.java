package com.example.projektpraktyczny.configuration;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.ApplicationUserRole;
import com.example.projektpraktyczny.repository1.ApplicationUserRepository;
import com.example.projektpraktyczny.repository1.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialDatabaseEntriesConfigurer implements ApplicationListener<ContextRefreshedEvent> {
    private final static String ADMIN_USERNAME = "admin";
    private final static String ADMIN_PASSWORD = "nimda";

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_USER = "ROLE_USER";

    private final static String[] AVAILABLE_ROLES = {ROLE_ADMIN, ROLE_USER};

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserRoleRepository applicationUserRoleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRoles();
        createUsers();
    }

    private void createUsers() {
        addUserIfNotExist(ADMIN_USERNAME, ADMIN_PASSWORD, Arrays.asList(AVAILABLE_ROLES));
        addUserIfNotExist("user", "user", Arrays.asList(ROLE_USER));
    }

    private void addUserIfNotExist(String username, String password, List<String> availableRoles) {
        if(!applicationUserRepository.existsByUsername(username)){

            Set<ApplicationUserRole> roles = new HashSet();
            for (String role : availableRoles) {
                Optional<ApplicationUserRole> optionalRole = applicationUserRoleRepository.findByName(role);
                if (optionalRole.isPresent()){
                    roles.add(optionalRole.get());
                }else{
                    log.error("Could not find role: " + role);
                }
            }

            ApplicationUser user = ApplicationUser.builder()
                    .username(username)
                    .password(bCryptPasswordEncoder.encode(password))
                    .roles(roles)
                    .build();

            applicationUserRepository.save(user);
        }
    }

    private void createRoles() {
        for (String availableRole : AVAILABLE_ROLES) {
            addIfNotExist(availableRole);
        }
    }

    private void addIfNotExist(String availableRole) {
        if(!applicationUserRoleRepository.existsByName(availableRole)){
            ApplicationUserRole role = ApplicationUserRole.builder().name(availableRole).build();
            applicationUserRoleRepository.save(role);
        }
    }
}
