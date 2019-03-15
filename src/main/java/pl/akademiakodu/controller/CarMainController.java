package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.repository.CarRepository;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.service.CarMainService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarMainController {

    CarMainService carMainService = new CarMainService();


    @Autowired
    private CarRepository carRepository;

    @GetMapping("/main")
    public String showAll(@RequestParam(required = false) String searchCategory,
                          @RequestParam(required = false) String searchWord,
                          ModelMap modelMap) {

        List<Car> cars;
        if (searchWord != null) {
            cars = carRepository.searchByKeyword(searchWord);
        } else {
             cars = carRepository.getAllCars();
        }
        modelMap.put("cars", cars);

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
