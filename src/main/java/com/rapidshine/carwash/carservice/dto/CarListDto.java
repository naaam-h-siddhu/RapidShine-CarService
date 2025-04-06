package com.rapidshine.carwash.carservice.dto;

import com.rapidshine.carwash.carservice.model.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CarListDto {
    private List<Car> cars;

}
