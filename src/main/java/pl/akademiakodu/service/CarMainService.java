package pl.akademiakodu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMainService {

   @Autowired
   CarRepository carRepository;

//   public Car findCarById(Integer id) {
//
//       Car car= new Car();
//
//       for (Car c : carRepository.getAllCars()) {
//           if (c.getId() == id) {
//               car = c;
//           }
//       }
//
//       return car;
//   }

}