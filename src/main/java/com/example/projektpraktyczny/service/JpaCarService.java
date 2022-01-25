package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.*;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.repository1.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaCarService implements CarService {
    final CarRepository carRepository;

    public JpaCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(car -> CarDto.builder()
                        .id(car.getId())
                        .type(car.getType())
                        .model(car.getModel())
                        .mark(car.getMark())
                        .build())
                        .collect(Collectors.toList());
    }

    @Override
    public void add(CarDto car) {
        Car createdCar = Car.builder()
                .type(car.getType())
                .model(car.getModel())
                .mark(car.getMark())
                .build();

        carRepository.save(createdCar);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
