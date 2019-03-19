package pl.akademiakodu.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    CarService carMainService;




    @Autowired
    private CarRepository carRepository;

    @GetMapping("/main")
    public String showAll(@RequestParam(required = false) String searchWord,
                          ModelMap modelMap) {

        List<Car> cars;
        if (searchWord != null) {
            cars = carRepository.searchByKeyword(searchWord);
        } else {
             cars = carRepository.findAll();
        }
        modelMap.put("cars", cars);

        return "allcars";}

    @GetMapping("/addcar")
    public String showAddForm(ModelMap modelMap) {

        modelMap.put("car", new Car());

        return "addform";
    }

//    @PostMapping("/caradded")
//    public String addNewCar(@RequestParam String type,
//                            @RequestParam String make,
//                            @RequestParam String model,
//                            @RequestParam Integer year,
//                            @RequestParam String fuel,
//                            @RequestParam Integer engine,
//                            @RequestParam Integer power,
//                            @RequestParam String location,
//                            @RequestParam Integer price,
//                            RedirectAttributes redirectAttributes) {
//
//            if (type!=null && make!=null && model!=null && year!=null && fuel!=null && engine!=null &&
//                   power !=null && location!=null && price!=null) {
//                carRepository.save(new Car(type, make, model, year, fuel, engine, power, location, price));
//                return "ready";
//            } else {
//                redirectAttributes.addFlashAttribute("message", "Wypełnij wszystkie pola.");
//                return "redirect:/addcar";
//            }
//
//    }


    @RequestMapping("/cars/{id}/delete")
    public String deleteCarById(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes) {

        Car car = carRepository.getCarById(id);

        redirectAttributes.addFlashAttribute("deletedcar", "Usunięto " + car.getMake() + " " + car.getModel() + ", rok " + car.getYear() + ".");

        carRepository.deleteById(id);

        return "redirect:/main";
    }



}
