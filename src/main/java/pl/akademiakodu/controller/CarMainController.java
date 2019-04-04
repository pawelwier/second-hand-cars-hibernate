package pl.akademiakodu.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.export.CreatorXLS;
import pl.akademiakodu.repository.CarRepository;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.service.CarService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CarMainController {

    int pageNum;

    private CarService carService;
    private CarRepository carRepository;

    public CarMainController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @GetMapping("/main/{page}")
    public String showAll(@RequestParam(required = false) String searchWord,
                          @PathVariable int page,
                          ModelMap modelMap) {

        pageNum = page;

        List<Car> cars;

        if (searchWord != null) {
            cars = carRepository.searchByKeyword(searchWord);
            modelMap.put("carList", cars);
        }
        if (searchWord == null || searchWord.isEmpty()) {
            PageRequest pageable = PageRequest.of(page - 1, 10);
            Page<Car> carPage = carService.getPaginatedCars(pageable);

            int totalPages = carPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                modelMap.put("pageNumbers", pageNumbers);
            }
            modelMap.put("activeCarList", true);
            modelMap.put("carList", carPage.getContent());
        }

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

    @GetMapping("/excel")
    public String createFile(RedirectAttributes redirectAttributes) throws NoSuchMethodException,
            IOException, IllegalAccessException, InvocationTargetException {

        CreatorXLS<Car> creatorXLS = new CreatorXLS<>(Car.class);
        creatorXLS.createFile(carService.showCars(), "src/main/resources", "CarList");

        redirectAttributes.addFlashAttribute("filemessage", "Plik utworzony");

        return "redirect:/main";
    }

//    @GetMapping("/car-list/page/{page}")
//    public String listCarsPageByPage(@PathVariable int page,
//                                           ModelMap modelMap) {
//
//        PageRequest pageable = PageRequest.of(page - 1, 10);
//        Page<Car> carPage = carService.getPaginatedCars(pageable);
//
//        int totalPages = carPage.getTotalPages();
//
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//            modelMap.put("pageNumbers", pageNumbers);
//            }
//        modelMap.put("activeCarList", true);
//        modelMap.put("carList", carPage.getContent());
//        return "car-list-paging";
//
//    }


}
