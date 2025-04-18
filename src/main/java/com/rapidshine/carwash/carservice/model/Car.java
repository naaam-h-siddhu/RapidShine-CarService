package com.rapidshine.carwash.carservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;

    public Car(String brand, String model, String licenceNumberPlate) {
        this.brand = brand;
        this.model = model;
        this.licenceNumberPlate = licenceNumberPlate;
    }


    private String model;

    @Column(unique = true,nullable = false)
    private String licenceNumberPlate;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonBackReference
    private Customer customer;
}
