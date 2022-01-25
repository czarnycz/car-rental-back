package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.CarBodyType;
import com.example.projektpraktyczny.model.CarMark;
import com.example.projektpraktyczny.model.CarModel;
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
    private CarMark mark;
    private CarModel model;
    private CarBodyType type;
    private Double price;
    private boolean cancelled;
    private boolean rented;
    private boolean returned;


}
