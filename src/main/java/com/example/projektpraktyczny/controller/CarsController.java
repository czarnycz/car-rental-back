package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.service.JpaCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {
    private final JpaCarService jpaCarService;

    @CrossOrigin()
    @GetMapping("")
    public List<CarDto> get(){
        return jpaCarService.findAll();
    }

    @CrossOrigin()
    @PostMapping("")
    public void add(@RequestBody CarDto car){
        jpaCarService.add(car);
    }

    @CrossOrigin()
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        jpaCarService.delete(id);
    }



}

