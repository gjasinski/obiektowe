package agh.cs.lab5;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab4.IWorldMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjasinski on 13.11.16.
 */
public abstract class AbstractWorldMap implements IWorldMap {
     protected List<Car> cars = new ArrayList<>();

    /**
     * Add a car the map.
     *
     * @param car
     *            The car to add.
     * @return True if the car was added.
     */
    public void add(Car car) {
        try {
            if (canMoveTo(car.getPosition())) {
                cars.add(car);
            } else {
                throw new IllegalArgumentException("This field is occupied");
            }
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }

    /**
     * Move the cars on the map according to the provided move directions. Every
     * n-th direction should be sent the n-th car on the map.
     *
     * @param directions
     *            Array of move directions.
     */
    public void run(MoveDirection[] directions){
        int quantityOfCars = cars.size();
        int i = 0;
        Car iCar;
        for (MoveDirection dir: directions) {
            iCar = cars.get(i);
            iCar.move(dir);
            i++;
            i=i%quantityOfCars;
        }
    }
}
