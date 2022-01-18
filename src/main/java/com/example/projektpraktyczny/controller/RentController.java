package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.exception.UnauthorizedUserException;
import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.service.ApplicationUserService;
import com.example.projektpraktyczny.service.RentDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {
    final RentDtoService rentDtoService;
    final ApplicationUserService applicationUserService;

    @PostMapping("/{reservationId}")
    public void addRentToReservation(@PathVariable Long reservationId, @RequestBody RentDto rent, Principal principal) {
        final Optional<Long> loggedInUserId = applicationUserService.getLoggedInUserId(principal);
        final boolean admin = applicationUserService.isAdmin(principal);
        if (loggedInUserId.isPresent() && admin) {
            rentDtoService.addRentToReservation(loggedInUserId.get(), reservationId, rent);
        } else if (!admin) {
            throw new UnauthorizedUserException("User " + principal + "can't perform this operation");
        }
    }
}
