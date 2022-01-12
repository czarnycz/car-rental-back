package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationDto {
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
}
