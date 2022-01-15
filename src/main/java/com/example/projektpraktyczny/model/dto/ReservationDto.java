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
public class ReservationDto {
    private Long id;
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
    private boolean cancelled;
    private CarBodyType type;
}
