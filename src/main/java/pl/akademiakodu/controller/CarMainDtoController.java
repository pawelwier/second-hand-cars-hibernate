package pl.akademiakodu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.model.dtos.CarDto;
import pl.akademiakodu.repository.CarRepository;
import pl.akademiakodu.service.CarService;

import java.util.List;

@Controller
public class CarMainDtoController {

    private CarService carService;
    private CarRepository carRepository;

    public CarMainDtoController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String showAllDto(@RequestParam(required = false) String searchWord,
                          ModelMap modelMap) {

        List<CarDto> cars;

         if (searchWord != null) cars = carService.searchByKeywordDto(searchWord);

         else cars = carService.showCarsDto();

        modelMap.put("carsdto", cars);

        return "index";
    }

    @GetMapping("/cars/{id}/details")
    public String showDtosDetails(@PathVariable int id,
                                  RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("car", carService.showOneCarById(id));

        return "redirect:/";
    }

}
