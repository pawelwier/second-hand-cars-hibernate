package pl.akademiakodu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.akademiakodu.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.make=?1 OR c.model=?1 OR c.type=?1 OR c.fuel=?1 OR c.engine=?1 OR " +
            "c.location=?1 OR c.power=?1 OR c.price=?1 OR c.year=?1")
    List<Car> searchByKeyword(String name);

    @Query("SELECT c FROM Car c WHERE c.id=?1")
    Car getCarById(Integer id);

    Page<Car> findAll(Pageable pageable);

}
