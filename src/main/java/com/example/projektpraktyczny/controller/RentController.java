package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.service.RentDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {
    final RentDtoService rentDtoService;

    @PostMapping("/{reservationId}")
    public void addRentToReservation(@PathVariable Long reservationId, @RequestBody RentDto rent){
        rentDtoService.addRentToReservation(reservationId,rent);
    }
}
