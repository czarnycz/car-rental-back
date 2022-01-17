package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Rent;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.RentDto;
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
    final RentDtoRepository rentRepository;

    public void addRentToReservation(Long reservationID, RentDto rent) {
        Reservation reservation = null;
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        if (reservation.getRent() != null) {
            reservation.getRent().setDateOfRent(rent.getDateOfRent());
            reservation.getRent().setComments(rent.getComments());
            reservation.getRent().setWorker(rent.getWorker());

            rentRepository.save(reservation.getRent());
        } else {
            Rent createdRent = Rent.builder()
                    .dateOfRent(rent.getDateOfRent())
                    .comments(rent.getComments())
                    .worker(rent.getWorker())
                    .build();

            rentRepository.save(createdRent);
        }
    }
}
