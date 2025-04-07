package com.rapidshine.carwash.carservice.service;

import com.rapidshine.carwash.carservice.dto.CarDto;
import com.rapidshine.carwash.carservice.dto.CarListDto;
import com.rapidshine.carwash.carservice.exceptions.CarAlreadyExists;
import com.rapidshine.carwash.carservice.exceptions.UserNotFoundException;
import com.rapidshine.carwash.carservice.model.Car;
import com.rapidshine.carwash.carservice.model.Customer;
import com.rapidshine.carwash.carservice.model.User;
import com.rapidshine.carwash.carservice.repository.CarRepository;
import com.rapidshine.carwash.carservice.repository.CarRepository;

import com.rapidshine.carwash.carservice.repository.CustomerRepository;
import com.rapidshine.carwash.carservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    public CarDto saveCar(String email,CarDto carDto) throws Exception{

        Customer customer = getCustomer(email);
        Car car = new Car(carDto.getBrand(),carDto.getModel(),carDto.getLicenceNumberPlate());
        if(carRepository.existsByLicenceNumberPlate(carDto.getLicenceNumberPlate())){
            throw  new CarAlreadyExists("Car with licence number "+carDto.getLicenceNumberPlate()+" already exists");
        }
        car.setCustomer(customer);
        carRepository.save(car);
        return carDto;


    }

    public CarListDto getAllCars(String email) throws Exception{
        Customer customer = getCustomer(email);
        List<Car> carList = carRepository.findAllByCustomerId(customer.getCustomerID());



        CarListDto carListDto = new CarListDto();
        carListDto.setCars(carList);
        return carListDto;


    }

    public Car getCarById(String email,Long id)throws  Exception{
        Customer customer = getCustomer(email);
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("Car not found with ID: "+id));
        if(!car.getCustomer().getCustomerID().equals(customer.getCustomerID())){
            throw new RuntimeException("You do not have access of the car with ID: "+id);
        }
        return car;

    }

    //edit car
    public CarDto editCarDetails(String email,Long id,CarDto carDto)throws  Exception{
        Customer customer = getCustomer(email);
        Car car = carRepository.findById(id).orElse(null);
        if(car == null){
            throw  new RuntimeException("Car does not exist with ID: "+id);
        }
        if(car != null && car.getCustomer().getCustomerID() != customer.getCustomerID()){
            throw new RuntimeException("You do not have access to edit the car");

        }
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setLicenceNumberPlate(carDto.getLicenceNumberPlate());

        carRepository.save(car);
        return  carDto;

    }

    // delete car
    public String deleteCar(String email,Long id)throws Exception{
        Customer customer = getCustomer(email);
        Car car = carRepository.findById(id).orElse(null);
        if(car == null){
            throw  new RuntimeException("Car does not exist with ID: "+car);
        }
        if(car != null && car.getCustomer().getCustomerID() != customer.getCustomerID()){
            throw new RuntimeException("You do not have access to edit the car");

        }
        carRepository.deleteById(id);
        return "Successfully deleted car with ID: "+id;
    }

    // Helper function to get the customer using the auth email
    private Customer getCustomer(String email) throws Exception{
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with "+email+
                " not found"));
        Long id = user.getId();
        Customer customer = customerRepository.findByUserId(id).orElseThrow(()-> new UserNotFoundException("Customer " +
                "with "+email+" not found"));
        return customer;
    }
}
