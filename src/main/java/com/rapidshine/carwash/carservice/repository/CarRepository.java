package com.rapidshine.carwash.carservice.repository;

import com.rapidshine.carwash.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByLicenceNumberPlate(String licenseNumber);
    @Query("SELECT c FROM Car c where c.customer.customerID = :id")
    List<Car> findAllByCustomerId(@Param("id") Long id);
    boolean existsByLicenceNumberPlate(String licenseNumber);
}
