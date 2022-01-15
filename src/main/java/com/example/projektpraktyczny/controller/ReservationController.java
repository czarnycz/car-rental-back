package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;
import com.example.projektpraktyczny.model.dto.ReservationDetailsDto;
import com.example.projektpraktyczny.model.dto.ReservationDto;
import com.example.projektpraktyczny.service.JpaReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final JpaReservationService jpaReservationService;

    @CrossOrigin()
    @GetMapping("")
    public List<ReservationDto> get(){
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

//        /reservations/selectCar/3
    @PostMapping("/selectCar/{reservationID}")
    public void addCarToReservation(@PathVariable Long reservationID, @RequestBody CarDto car){
        // Spodziewamy się że obiekt car będzie zawierał 3 pola: mark, model, type
        jpaReservationService.addCarToReservation(reservationID, car);
    }

}