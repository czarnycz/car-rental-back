package com.example.projektpraktyczny.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfReturn;
    private double additionalPayment;
    private String comments;

    @OneToOne
    private Reservation reservation;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private ApplicationUser worker;
}
