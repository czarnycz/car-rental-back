package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.CarBodyType;
import com.example.projektpraktyczny.model.CarMark;
import com.example.projektpraktyczny.model.CarModel;
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

    private CarMark mark;

    private CarModel model;

    private CarBodyType type;
}
