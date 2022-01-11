package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Reservation;
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
    public void add(@RequestBody Reservation reservation){
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

    @PostMapping("/attendee/{clientId}/{reservationID}")
    public void addClientToReservation(@PathVariable  Long clientId, @PathVariable Long reservationID){
        jpaReservationService.addClientToReservation(clientId, reservationID);
    }


}