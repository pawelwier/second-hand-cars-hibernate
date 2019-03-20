package pl.akademiakodu.service;

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

   public Car showOneCarById(int id) {
      return carRepository.findById(id).get();
   }

   public List<Car> showCars() {
      return carRepository.findAll();
   }

   public List<CarDto> searchByKeywordDto(String word) {

      List<CarDto> emptyList = new ArrayList<>();

      List<CarDto> list = carRepository.findAll()
              .stream()
              .map(from -> carMapper.map(from))
              .collect(Collectors.toList());

      for (CarDto c : list) {
         if (c.getMake().contains(word) || c.getModel().contains(word) || (""+c.getPrice()).contains(word)) {
            emptyList.add(c);
         }
      }
      return emptyList;
   }

   public List<CarDto> showCarsDto() {
      return carRepository
              .findAll()
              .stream()
              .map(from -> carMapper.map(from))
              .collect(Collectors.toList());
   }

//   public List<CarDto> showCarsDto2() {
//      List<CarDto> carDtoList = new ArrayList<>();
//      for (Car c : carRepository.findAll()) {
//         carDtoList.add(carMapper.map(c));
//      }
//      return carDtoList;
//   }

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