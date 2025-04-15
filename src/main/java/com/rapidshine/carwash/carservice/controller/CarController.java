package com.rapidshine.carwash.carservice.controller;

import com.rapidshine.carwash.carservice.dto.CarDto;
import com.rapidshine.carwash.carservice.dto.CarListDto;
import com.rapidshine.carwash.carservice.model.Car;
import com.rapidshine.carwash.carservice.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {


    @Autowired
    private CarService carService;

    @GetMapping("/health")
    public String hello() {
        return "Hello World";
    }
    @PostMapping
    public CarDto addCar(Authentication authentication, @RequestBody CarDto carDto) throws Exception {
        String email = authentication.getName();

        return carService.saveCar(email,carDto);

    }

    @GetMapping
    public CarListDto getAllCar(Authentication authentication) throws Exception {
        String email = authentication.getName();
        return carService.getAllCars(email);
    }

    @GetMapping("/{id}")
    public Car getCar(Authentication authentication, @PathVariable Long id) throws Exception {
        return carService.getCarById(authentication.getName(),id);
    }

    @PutMapping("/{id}")
    public CarDto editCarDetails(Authentication authentication,@PathVariable Long id,@RequestBody CarDto carDto) throws Exception{
        return carService.editCarDetails(authentication.getName(),id,carDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(Authentication authentication,@PathVariable Long id) throws Exception {
        return carService.deleteCar(authentication.getName(), id);
    }



}
