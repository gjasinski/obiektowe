package agh.cs.lab5;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab6.AbstractWorldMapElement;
import agh.cs.lab7.IPositionChangeListener;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gjasinski on 13.11.16.
 */
public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeListener {
    protected List<Car> cars = new ArrayList<>();
    protected Map<Position, AbstractWorldMapElement> mapElement = new LinkedHashMap<>();

    public void add(Car car) {
            if (canMoveTo(car.getPosition())) {
                cars.add(car);
                mapElement.put(car.getPosition(), car);
            } else {
                throw new IllegalArgumentException("AbstractWorldMap.add - This field is occupied");
            }
    }

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

    public void positionChanged(Position oldPosition, Position newPosition){
        this.mapElement.put(newPosition, mapElement.get(oldPosition));
        this.mapElement.remove(oldPosition);
    }

    public boolean isOccupied(Position position){
        return mapElement.get(position) != null;
    }

    public Object objectAt(Position position){
        return mapElement.get(position);
    }
}
