package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.repository1.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JpaCarService implements CarService{
    final CarRepository carRepository;

    public JpaCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void add(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
