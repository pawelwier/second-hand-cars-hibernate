package pl.akademiakodu.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
