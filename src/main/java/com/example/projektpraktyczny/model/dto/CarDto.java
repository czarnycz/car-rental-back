package com.example.projektpraktyczny.model.dto;

import com.example.projektpraktyczny.model.CarBodyType;
import com.example.projektpraktyczny.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String mark;

    private String model;

    private CarBodyType type;
}
