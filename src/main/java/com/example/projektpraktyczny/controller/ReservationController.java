package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;
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
    public List<Reservation> get(){
        return jpaReservationService.findAll();
    }

    @CrossOrigin()
    @PostMapping("")
    public void add(@RequestBody CreateReservationDto reservation){
        jpaReservationService.add(reservation);
    }

    @CrossOrigin()
    @PostMapping("/{id}")
    public void cancel(@PathVariable Long id){
        jpaReservationService.cancel(id);
    }

    @CrossOrigin()
    @GetMapping("/{clientId}")
    public List<Reservation> findClientReservation(@PathVariable Long clientId){
        return jpaReservationService.findClientReservation(clientId);
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