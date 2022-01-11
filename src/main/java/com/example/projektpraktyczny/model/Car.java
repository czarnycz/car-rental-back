package com.example.projektpraktyczny.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;

    private String model;

    private CarBodyType type;



    @OneToOne
    private Reservation reservation;


}
