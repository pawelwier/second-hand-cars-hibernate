package pl.akademiakodu.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.model.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
