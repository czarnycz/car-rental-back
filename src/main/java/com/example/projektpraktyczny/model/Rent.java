package com.example.projektpraktyczny.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfRent;
    private String comments;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private ApplicationUser worker;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne
    private Reservation reservation;

}
