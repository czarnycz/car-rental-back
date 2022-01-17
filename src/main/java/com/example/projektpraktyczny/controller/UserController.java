package com.example.projektpraktyczny.controller;


import com.example.projektpraktyczny.model.dto.ApplicationUserDto;
import com.example.projektpraktyczny.model.dto.RegisterApplicationUserDto;
import com.example.projektpraktyczny.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping("")
    public ApplicationUserDto get(Principal principal){
        Optional<Long> userIdOptional = applicationUserService.getLoggedInUserId(principal);
        if(userIdOptional.isPresent()){
            Long loggedInUserId = userIdOptional.get();
            return applicationUserService.getLoggedInUserDto(loggedInUserId);
        }
        throw new EntityNotFoundException("Unable to find user.");
    }

    @CrossOrigin
    @PostMapping("/register")
    public void get(@RequestBody RegisterApplicationUserDto dto){
        applicationUserService.register(dto);
    }
}