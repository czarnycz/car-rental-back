package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.Client;
import com.example.projektpraktyczny.model.Reservation;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.model.dto.CreateReservationDto;
import com.example.projektpraktyczny.model.dto.ReservationDetailsDto;
import com.example.projektpraktyczny.model.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    Long add(CreateReservationDto reservation);
    List<ReservationDto> findAll();
    List<Reservation> findClientReservation(Long clientId);
    void cancel(Long id);
    void addClientToReservation(Long clientId, Long reservationID);
    void addCarToReservation(Long reservationId, CarDto car);
    public ReservationDetailsDto findReservation(Long reservationId);
}
