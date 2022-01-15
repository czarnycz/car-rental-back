package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.CarBodyType;
import com.example.projektpraktyczny.model.Reservation;
import lombok.*;

import javax.persistence.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;

    private String mark;

    private String model;

    private CarBodyType type;
}
