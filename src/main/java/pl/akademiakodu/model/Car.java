package pl.akademiakodu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    private String make;

    private String model;

    private int year;

    private String fuel;

    private int engine;

    private int power;

    private String location;

    private int price;

}
