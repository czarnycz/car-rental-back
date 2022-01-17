package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.*;
import com.example.projektpraktyczny.service.ApplicationUserService;
import com.example.projektpraktyczny.service.JpaReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final JpaReservationService jpaReservationService;
    private final ApplicationUserService applicationUserService;

    @CrossOrigin()
    @GetMapping("")
    public List<ReservationDto> get(Principal principal){
        Optional<Long> userIdOptional = applicationUserService.getLoggedInUserId(principal);
        log.info("Zalogowani? : " + userIdOptional);
        return jpaReservationService.findAll();
    }

    @CrossOrigin()
    @PostMapping("")
    public Long add(@RequestBody CreateReservationDto reservation){
        return jpaReservationService.add(reservation);
    }

    @CrossOrigin()
    @PostMapping("/cancel/{id}")
    public void cancel(@PathVariable Long id){
        jpaReservationService.cancel(id);
    }

    @CrossOrigin()
    @GetMapping("/{reservationId}")
    public ReservationDetailsDto findReservation(@PathVariable Long reservationId){
        return jpaReservationService.findReservation(reservationId);
    }

    @PostMapping("/selectCar/{clientId}/{reservationID}")
    public void addClientToReservation(@PathVariable  Long clientId, @PathVariable Long reservationID){
        jpaReservationService.addClientToReservation(clientId, reservationID);
    }

    @PostMapping("/selectCar/{reservationID}")
    public void addCarToReservation(@PathVariable Long reservationID, @RequestBody CarDto car){
        // Spodziewamy się że obiekt car będzie zawierał 3 pola: mark, model, type
        jpaReservationService.addCarToReservation(reservationID, car);
    }


}