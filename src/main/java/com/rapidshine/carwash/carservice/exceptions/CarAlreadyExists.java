package com.rapidshine.carwash.carservice.exceptions;

import com.rapidshine.carwash.carservice.model.Car;

public class CarAlreadyExists extends Exception{
    public CarAlreadyExists(String message){
        super(message);
    }
}
