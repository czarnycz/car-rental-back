package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}

