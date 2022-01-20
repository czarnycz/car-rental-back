package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.CarReturn;
import com.example.projektpraktyczny.model.Rent;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.RentDto;
import com.example.projektpraktyczny.model.dto.ReturnDto;
import com.example.projektpraktyczny.repository1.ApplicationUserRepository;
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
    final ApplicationUserRepository applicationUserRepository;

    public void addReturnToReservation(Long workerId,Long reservationID, ReturnDto returnDto) {
        ApplicationUser worker = null;
        Reservation reservation = null;

        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        final Optional<ApplicationUser> applicationUserOptional = applicationUserRepository.findById(workerId);
        if(applicationUserOptional.isPresent()){
            worker = applicationUserOptional.get();
        } else {
            throw new EntityNotFoundException("Worker with ID: " + reservationID + " not found");
        }


        if (reservation.getAReturn() != null) {
            reservation.getAReturn().setWorker(worker);
            reservation.getAReturn().setReservation(reservation);
            reservation.getAReturn().setDateOfReturn(returnDto.getDateOfReturn());
            reservation.getAReturn().setAdditionalPayment(returnDto.getAdditionalPayment());
            reservation.getAReturn().setComments(returnDto.getComments());

            returnRepository.save(reservation.getAReturn());
        } else {
            CarReturn createdReturn = CarReturn.builder()
                    .worker(worker)
                    .reservation(reservation)
                    .dateOfReturn(returnDto.getDateOfReturn())
                    .additionalPayment(returnDto.getAdditionalPayment())
                    .comments(returnDto.getComments())
                    .build();

            returnRepository.save(createdReturn);
        }
    }
}
