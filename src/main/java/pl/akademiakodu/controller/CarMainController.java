package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.repository.CarRepository;
import pl.akademiakodu.model.Car;

import javax.validation.Valid;

@Controller
public class CarMainController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/main")
    public String showAll(ModelMap modelMap) {

        modelMap.put("cars", carRepository.findAll());

        return "allcars";}

    @GetMapping("/addcar")
    public String showAddForm(ModelMap modelMap) {

        modelMap.put("car", new Car());

        return "addform";
    }

    @PostMapping("/caradded")
    public String addNewCar(@Valid Car car) {
            carRepository.save(car);
      return "ready";
    }





}
