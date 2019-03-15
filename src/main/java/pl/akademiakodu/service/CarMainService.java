package pl.akademiakodu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMainService {

   @Autowired
   CarRepository carRepository;

   public List<Car> searchByKeyword(String word) {

       List<Car> list = new ArrayList<>();

       for (Car c : list) {
           if (String.valueOf(c.getEngine()).equals(word) || c.getMake().equals(word)) {
               list.add(c);
           }
       }

       return list;
   }

}