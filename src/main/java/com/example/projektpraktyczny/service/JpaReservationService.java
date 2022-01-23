package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.CarBodyType;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;
import com.example.projektpraktyczny.model.dto.ReservationDetailsDto;
import com.example.projektpraktyczny.model.dto.ReservationDto;
import com.example.projektpraktyczny.repository1.ApplicationUserRepository;
import com.example.projektpraktyczny.repository1.CarRepository;
import com.example.projektpraktyczny.repository1.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaReservationService implements ReservationService {

    final ApplicationUserRepository applicationUserRepository;
    final ReservationRepository reservationRepository;
    final CarRepository carRepository;
    final PriceCalculator priceCalculator;

    @Override
    public Long add(CreateReservationDto reservation, Long clientId) {
        ApplicationUser client = null;
        Optional<ApplicationUser> clientOptional = applicationUserRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            client = clientOptional.get();

        } else {
            throw new EntityNotFoundException("Client with ID: " + clientId + " not found");
        }

        Reservation createdReservation = Reservation.builder()
                .dateOfReservation(LocalDate.now())
                .startOfReservation(reservation.getStartOfReservation())
                .endOfReservation(reservation.getEndOfReservation())
                .client(client)
                .build();

        return reservationRepository.save(createdReservation).getId();
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservation -> ReservationDto.builder()
                        .startOfReservation(reservation.getStartOfReservation())
                        .endOfReservation(reservation.getEndOfReservation())
                        .cancelled(reservation.isCancelled())
                        .type(reservation.getCar() != null ? reservation.getCar().getType() : CarBodyType.DID_NOT_SET)
                        .id(reservation.getId())
                        .price(reservation.getPrice())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> findClientReservation(Long clientId) {
        Optional<ApplicationUser> clientOptional = applicationUserRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            ApplicationUser client = clientOptional.get();
            return reservationRepository.findByClient(client).stream()
                    .map(reservation -> ReservationDto.builder()
                            .startOfReservation(reservation.getStartOfReservation())
                            .endOfReservation(reservation.getEndOfReservation())
                            .cancelled(reservation.isCancelled())
                            .type(reservation.getCar() != null ? reservation.getCar().getType() : CarBodyType.DID_NOT_SET)
                            .id(reservation.getId())
                            .price(reservation.getPrice())
                            .build()).collect(Collectors.toList());
        }
        throw new EntityNotFoundException("Client with ID: " + clientId + " not found");
    }

    @Override
    public ReservationDetailsDto findReservation(Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            return ReservationDetailsDto.builder()
                    .id(reservation.getId())
                    .dateOfReservation(reservation.getDateOfReservation())
                    .startOfReservation(reservation.getStartOfReservation())
                    .endOfReservation(reservation.getEndOfReservation())
                    .mark(reservation.getCar() != null ? reservation.getCar().getMark() : "Didn't Set")
                    .model(reservation.getCar() != null ? reservation.getCar().getModel() : "Didn't Set")
                    .type(reservation.getCar() != null ? reservation.getCar().getType() : CarBodyType.DID_NOT_SET)
                    .cancelled(reservation.isCancelled())
                    .rented(reservation.getRent() != null)
                    .returned(reservation.getAReturn() != null)
                    .price(reservation.getPrice())
                    .build();
        }
        throw new EntityNotFoundException("Reservation with ID: " + reservationId + " not found");
    }

    @Override
    public void cancel(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setCancelled(true);
            reservationRepository.save(reservation);
        }
    }

    @Override
    public void addCarToReservation(Long reservationID, CarDto car) {
        Reservation reservation = null;
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        if (reservation.getCar() != null) {
            reservation.getCar().setMark(car.getMark());
            reservation.getCar().setModel(car.getModel());
            reservation.getCar().setType(car.getType());
            carRepository.save(reservation.getCar());
        } else {
            Car createdCar = Car.builder()
                    .reservation(reservation)
                    .model(car.getModel())
                    .mark(car.getMark())
                    .type(car.getType())
                    .build();

            carRepository.save(createdCar);
        }

        reservation.setPrice(priceCalculator.calculatePrice(reservationID));
        reservationRepository.save(reservation);
    }

    @Override
    public void addClientToReservation(Long clientId, Long reservationID) {

        ApplicationUser client = null;
        Reservation reservation = null;

        Optional<ApplicationUser> clientOptional = applicationUserRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            client = clientOptional.get();

        } else {
            throw new EntityNotFoundException("Client with ID: " + clientId + " not found");
        }

        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if (reservationOptional.isPresent()) {
            reservation = reservationOptional.get();

        } else {
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }
        reservation.setClient(client);

        reservationRepository.save(reservation);
    }


}
