package com.example.projektpraktyczny.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfReservation;
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
    private double price;
    private boolean cancelled;

    @OneToOne
    private Car car;

    @OneToOne
    private Client client;

    @OneToOne
    private Rent rent;

    @OneToOne
    private CarReturn aReturn;

    @OneToOne
    private Worker worker;
}