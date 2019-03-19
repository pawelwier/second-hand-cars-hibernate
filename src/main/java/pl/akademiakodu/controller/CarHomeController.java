package pl.akademiakodu.controller;

import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.model.dtos.CarDto;
import pl.akademiakodu.service.CarService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CarHomeController {

    private CarService carService;

    public CarHomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> showAllCars() {
        return carService.showCars();
    }

    @GetMapping("/dto/cars")
    public List<CarDto> showAllCarsDto() {
        return carService.showCarsDto();
    }

    @PostMapping("/cars/add")
    public Car addCarToDatabase(@RequestParam String type,
                            @RequestParam String make,
                            @RequestParam String model,
                            @RequestParam Integer year,
                            @RequestParam String fuel,
                            @RequestParam Integer engine,
                            @RequestParam Integer power,
                            @RequestParam String location,
                            @RequestParam Integer price) {
        return carService.addNewCar(type, make, model, year, fuel, engine, power, location, price);

    }



}
