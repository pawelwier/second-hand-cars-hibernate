package pl.akademiakodu.model;

import java.util.List;

public class CarList {

    List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public CarList(List<Car> carList) {
        this.carList = carList;
    }

    public CarList() {

    }
}
