package pl.akademiakodu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.akademiakodu.mappers.CarMapper;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.model.dtos.CarDto;
import pl.akademiakodu.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

   private CarRepository carRepository;
   private CarMapper carMapper;

   public CarService(CarRepository carRepository, CarMapper carMapper) {
      this.carRepository = carRepository;
      this.carMapper = carMapper;
   }

   public List<Car> showCars() {
      return carRepository.findAll();
   }

   public List<CarDto> showCarsDto() {
      return carRepository
              .findAll()
              .stream()
              .map(carMapper::map)
              .collect(Collectors.toList());
   }

   public Car addNewCar(String type, String make, String model, Integer year, String fuel, Integer engine,
                        Integer power, String location, Integer price) {

      return carRepository.save(Car
              .builder()
              .type(type)
              .make(make)
              .model(model)
              .year(year)
              .fuel(fuel)
              .engine(engine)
              .power(power)
              .location(location)
              .price(price)
              .build());
   }


}