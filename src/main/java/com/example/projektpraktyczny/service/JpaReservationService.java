package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.Client;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;
import com.example.projektpraktyczny.repository1.CarRepository;
import com.example.projektpraktyczny.repository1.ClientRepository;
import com.example.projektpraktyczny.repository1.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaReservationService implements ReservationService{

    final ReservationRepository reservationRepository;
    final CarRepository carRepository;
    final ClientRepository clientRepository;

    @Override
    public void add(CreateReservationDto reservation) {
        Reservation createdReservation = Reservation.builder()
                .dateOfReservation(LocalDate.now())
                .startOfReservation(reservation.getStartOfReservation())
                .endOfReservation(reservation.getEndOfReservation())
                .build();

        reservationRepository.save(createdReservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findClientReservation(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            List<Reservation> byClient = reservationRepository.findByClient(client);
            return byClient;
        }
        throw new EntityNotFoundException("Client with ID: " + clientId + " not found");
    }

    @Override
    public void cancel(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservation.setCancelled(true);
            reservationRepository.save(reservation);
        }
    }

    @Override
    public void addCarToReservation(Long reservationID, CarDto car) {
        Reservation reservation = null;
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if(reservationOptional.isPresent()){
            reservation = reservationOptional.get();

        }else{
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }

        Car createdCar = Car.builder()
                .reservation(reservation)
                .model(car.getModel())
                .mark(car.getModel())
                .type(car.getType())
                .build();

        carRepository.save(createdCar);
    }

    @Override
    public void addClientToReservation(Long clientId, Long reservationID) {

        Client client = null;
        Reservation reservation = null;

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if(clientOptional.isPresent()){
             client = clientOptional.get();

        }else{
            throw new EntityNotFoundException("Client with ID: " + clientId + " not found");
        }
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);
        if(reservationOptional.isPresent()){
            reservation = reservationOptional.get();

        }else{
            throw new EntityNotFoundException("Reservation with ID: " + reservationID + " not found");
        }
        reservation.setClient(client);
        reservationRepository.save(reservation);
    }
}
