package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.Client;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;

import java.util.List;

public interface ReservationService {
    // dodawanie nowych , listowanie rezerwacji konkretnego clienta, anulowanie, listowanie wszytskich
    // dodawanie samochodu do rezerwacji
    // dodawanie klienta do rezerwacji

    void add(CreateReservationDto reservation);
    List<Reservation> findAll();
    List<Reservation> findClientReservation(Long clientId);
    void cancel(Long id);
    void addClientToReservation(Long clientId, Long reservationID);
    void addCarToReservation(Long reservationId, CarDto car);

}
