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
public class ReservationDto {
    private Long id;
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
    private boolean cancelled;
    private Double price;
    private CarBodyType type;
    private CarMark mark;
    private CarModel model;
}
