package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    void add(Car car);
    void delete(Long id);
}
