package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.Rent;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.repository1.ApplicationUserRepository;
import com.example.projektpraktyczny.repository1.RentDtoRepository;
import com.example.projektpraktyczny.repository1.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentDtoService {
    final ReservationRepository reservationRepository;
    final ApplicationUserRepository applicationUserRepository;
    final RentDtoRepository rentRepository;
    final PriceCalculator priceCalculator;

    public void addRentToReservation(Long workerId, Long reservationID, RentDto rent) {
        ApplicationUser worker = null;
        Reservation reservation = null;
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        Optional<ApplicationUser> applicationUserOptional = applicationUserRepository.findById(workerId);
        if (applicationUserOptional.isPresent()) {
            worker = applicationUserOptional.get();

        } else {
            throw new EntityNotFoundException("Worker with ID: " + reservationID + " not found");
        }

        if (reservation.getRent() != null) {
            reservation.getRent().setReservation(reservation);
            reservation.getRent().setDateOfRent(rent.getDateOfRent());
            reservation.getRent().setComments(rent.getComments());
            reservation.getRent().setWorker(worker);

            rentRepository.save(reservation.getRent());
        } else {
            Rent createdRent = Rent.builder()
                    .reservation(reservation)
                    .dateOfRent(rent.getDateOfRent())
                    .comments(rent.getComments())
                    .worker(worker)
                    .build();

            rentRepository.save(createdRent);
        }

        reservation.setPrice(priceCalculator.calculatePrice(reservationID));
        reservationRepository.save(reservation);
    }
}
