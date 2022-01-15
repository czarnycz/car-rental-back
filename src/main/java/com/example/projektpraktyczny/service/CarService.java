package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> findAll();
    void add(CarDto car);
    void delete(Long id);
}
