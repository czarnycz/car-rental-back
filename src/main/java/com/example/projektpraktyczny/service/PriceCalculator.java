package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.repository1.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@AllArgsConstructor
public class PriceCalculator {
    public double calculatePrice(Reservation reservation) {
        long daysBetween = DAYS.between(reservation.getStartOfReservation(), reservation.getEndOfReservation());
        if (reservation.getAReturn() != null && reservation.getCar() != null) {
            double pricePerDay = reservation.getCar().getType().getPrice();
            double returnAddedPay = reservation.getAReturn().getAdditionalPayment();
            return (daysBetween * pricePerDay) + returnAddedPay;
        } else if (reservation.getCar() != null) {
            double pricePerDay = reservation.getCar().getType().getPrice();
            return (daysBetween * pricePerDay);
        } else {
            return 0;
        }
    }
}
