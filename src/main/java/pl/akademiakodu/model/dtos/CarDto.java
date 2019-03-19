package pl.akademiakodu.model.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarDto {

    private String make;

    private String model;

    private int price;
}
