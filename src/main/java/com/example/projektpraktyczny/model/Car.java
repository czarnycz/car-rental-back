package com.example.projektpraktyczny.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@Entity
@Table(name = "car")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;

    private String model;

    @Enumerated(EnumType.STRING)
    private CarBodyType type;



    @OneToOne
    private Reservation reservation;


}
