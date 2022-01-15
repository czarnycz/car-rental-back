package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.CarBodyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailsDto {
    private Long id;
    private LocalDate dateOfReservation;
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
    private String mark;
    private String model;
    private CarBodyType type;
    private Double price;
    private boolean cancelled;


}
