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

    @Enumerated(EnumType.STRING)
    private CarMark mark;

    @Enumerated(EnumType.STRING)
    private CarModel model;

    @Enumerated(EnumType.STRING)
    private CarBodyType type;



    @OneToOne
    private Reservation reservation;


}
