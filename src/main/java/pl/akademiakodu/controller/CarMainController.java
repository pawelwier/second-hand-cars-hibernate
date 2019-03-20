package pl.akademiakodu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.repository.CarRepository;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.service.CarService;

import java.util.List;

@Controller
public class CarMainController {

    private CarService carService;
    private CarRepository carRepository;

    public CarMainController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @GetMapping("/main")
    public String showAll(@RequestParam(required = false) String searchWord,
                          ModelMap modelMap) {

        List<Car> cars;

        if (searchWord != null) cars = carRepository.searchByKeyword(searchWord);
        else cars = carRepository.findAll();

        modelMap.put("cars", cars);

        return "allcars";}

    @GetMapping("/addcar")
    public String showAddForm() {

        return "addform";
    }

    @PostMapping("/caradded")
    public String addCar(@RequestParam(required = false) String type,
                            @RequestParam(required = false)  String make,
                            @RequestParam(required = false)  String model,
                            @RequestParam(required = false)  Integer year,
                            @RequestParam(required = false)  String fuel,
                            @RequestParam(required = false)  Integer engine,
                            @RequestParam(required = false)  Integer power,
                            @RequestParam(required = false)  String location,
                            @RequestParam(required = false)  Integer price,
                            RedirectAttributes redirectAttributes) {

            if (type!=null && make!=null && model!=null && year!=null && fuel!=null && engine!=null &&
                   power !=null && location!=null && price!=null) {
                carService.addNewCar(type, make, model, year, fuel, engine, power, location, price);
                return "redirect:/main";
            } else {
                redirectAttributes.addFlashAttribute("message", "Wypełnij wszystkie pola.");
                return "redirect:/addcar";
            }

    }

    @RequestMapping("/cars/{id}/delete")
    public String deleteCarById(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes) {

        Car car = carRepository.getCarById(id);

        redirectAttributes.addFlashAttribute("deletedcar", "Usunięto " + car.getMake() + " " +
                car.getModel() + ", rok " + car.getYear() + ".");

        carRepository.deleteById(id);

        return "redirect:/main";
    }




}
