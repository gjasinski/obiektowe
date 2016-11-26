package agh.cs.lab5;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab6.AbstractWorldMapElement;
import agh.cs.lab6.IPositionChangeListener;


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

    /**
     * Add a car the map.
     *
     * @param car
     *            The car to add.
     * @return True if the car was added.
     */
    public void add(Car car) {
            if (canMoveTo(car.getPosition())) {
                cars.add(car);
                mapElement.put(car.getPosition(), car);
            } else {
                throw new IllegalArgumentException("AbstractWorldMap.add - This field is occupied");
            }
    }

    /**
     * Move the mapElement on the map according to the provided move directions. Every
     * n-th direction should be sent the n-th car on the map.
     *
     * @param directions
     *            Array of move directions.
     */
    public void run(MoveDirection[] directions){
        //throw new IllegalArgumentException("AbstractWorldMap.run - not implemented");
        int quantityOfCars = cars.size();
        int i = 0;
        Car iCar;
        MapVisualizer mapVisualizer = new MapVisualizer();
        for (MoveDirection dir: directions) {
            System.out.println(mapVisualizer.dump(this, new Position(0, 0), new Position(10, 5)));
            iCar = cars.get(i);
            iCar.move(dir);
            i++;
            i=i%quantityOfCars;
        }
    }

    public void positionChanged(Position oldPosition, Position newPosition){
        mapElement.put(newPosition, mapElement.get(oldPosition));
        mapElement.remove(oldPosition);
    }
}
