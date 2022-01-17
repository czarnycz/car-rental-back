package com.example.projektpraktyczny.controller;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.dto.CarDto;
import com.example.projektpraktyczny.service.ApplicationUserService;
import com.example.projektpraktyczny.service.JpaCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {
    private final JpaCarService jpaCarService;
    private final ApplicationUserService applicationUserService;


    @GetMapping("")
    public List<CarDto> get(Principal principal){
        Optional<Long> userIdOptional = applicationUserService.getLoggedInUserId(principal);
        log.info("Zalogowani? : " + userIdOptional);
        return jpaCarService.findAll();
    }


    @PostMapping("")
    public void add(@RequestBody CarDto car){
        jpaCarService.add(car);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        jpaCarService.delete(id);
    }



}

