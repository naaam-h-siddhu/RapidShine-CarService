package com.rapidshine.carwash.carservice.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private String brand;
    private String model;
    private String carType;
    private String licenceNumberPlate;

}
