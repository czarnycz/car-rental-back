package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.exception.UnauthorizedUserException;
import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.model.dto.ReturnDto;
import com.example.projektpraktyczny.service.ApplicationUserService;
import com.example.projektpraktyczny.service.ReturnDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/return")
@RequiredArgsConstructor
public class ReturnController {
    final ReturnDtoService returnDtoService;
    final ApplicationUserService applicationUserService;

    @PostMapping("/{reservationId}")
    public void addReturnToReservation(@PathVariable Long reservationId, @RequestBody ReturnDto returnDto, Principal principal) {
        final Optional<Long> loggedInUserId = applicationUserService.getLoggedInUserId(principal);
        final boolean admin = applicationUserService.isAdmin(principal);
        if (loggedInUserId.isPresent() && admin) {
            returnDtoService.addReturnToReservation(loggedInUserId.get(), reservationId, returnDto);
        } else if (!admin) {
            throw new UnauthorizedUserException("User " + principal + "can't perform this operation");
        }
    }
}
