package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.CarReturn;
import com.example.projektpraktyczny.model.Rent;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.model.dto.ReturnDto;
import com.example.projektpraktyczny.repository1.ReservationRepository;
import com.example.projektpraktyczny.repository1.ReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReturnDtoService {

    final ReservationRepository reservationRepository;
    final ReturnRepository returnRepository;

    public void addReturnToReservation(Long reservationID, ReturnDto returnDto) {

        Reservation reservation = null;
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        if (reservation.getAReturn() != null) {
            reservation.getAReturn().setDateOfReturn(returnDto.getDateOfReturn());
            reservation.getAReturn().setAdditionalPayment(returnDto.getAdditionalPayment());
            reservation.getAReturn().setComments(returnDto.getComments());

            returnRepository.save(reservation.getAReturn());
        } else {
            CarReturn createdReturn = CarReturn.builder()
                    .reservation(reservation)
                    .dateOfReturn(returnDto.getDateOfReturn())
                    .comments(returnDto.getComments())
                    .additionalPayment(returnDto.getAdditionalPayment())
                    .build();

            returnRepository.save(createdReturn);
        }
    }
}
