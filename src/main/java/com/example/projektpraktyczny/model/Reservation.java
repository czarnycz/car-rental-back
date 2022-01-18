package com.example.projektpraktyczny.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfReservation;
    private LocalDate startOfReservation;
    private LocalDate endOfReservation;
    private Double price;
    private boolean cancelled;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "reservation")
    private Car car;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne()
    private ApplicationUser client;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "reservation")
    private Rent rent;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "reservation")
    private CarReturn aReturn;
}
