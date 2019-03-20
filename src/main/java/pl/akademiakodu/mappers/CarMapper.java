package pl.akademiakodu.mappers;

import org.springframework.stereotype.Component;
import pl.akademiakodu.commons.Mapper;
import pl.akademiakodu.model.Car;
import pl.akademiakodu.model.dtos.CarDto;

@Component
public class CarMapper implements Mapper<Car, CarDto> {

    @Override
    public CarDto map(Car from) {
        return CarDto
                .builder()
                .id(from.getId())
                .make(from.getMake())
                .model(from.getModel())
                .price(from.getPrice())
                .build();
    }

    @Override
    public Car reverse(CarDto to) {
        return Car
                .builder()
                .id(to.getId())
                .make(to.getMake())
                .model(to.getModel())
                .price(to.getPrice())
                .build();
    }
}
