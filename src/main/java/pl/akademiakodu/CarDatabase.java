package pl.akademiakodu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.repository.CarRepository;

@Component
public class CarDatabase implements CommandLineRunner {


    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) {

        Car car1 = new Car("osobowy", "mercedes", "a klasa", 2012, "diesel", 3196, 182, "Poznań", 21000);
        Car car2 = new Car("dostawczy", "volkswagen", "crafter", 2015, "diesel", 2198, 160, "Wrocław", 54000);
        Car car3 = new Car("osobowy", "honda", "civic", 2007, "benzyna", 1499, 96, "Warszawa", 13000);
        Car car4 = new Car("osobowy", "daewoo", "lanos", 2001, "benzyna", 1200, 77, "Warszawa", 5800);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
    }
}
