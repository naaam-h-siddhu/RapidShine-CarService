package com.rapidshine.carwash.carservice.dto;

import com.rapidshine.carwash.carservice.model.Car;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CarListDto {
    public CarListDto(List<Car> cars) {
        this.cars = cars;
    }

    public CarListDto() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    private List<Car> cars;

}
