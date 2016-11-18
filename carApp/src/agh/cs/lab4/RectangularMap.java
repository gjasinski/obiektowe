package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Position;

import java.util.ArrayList;
import java.util.List;

import agh.cs.lab2.Car;
import agh.cs.lab5.AbstractWorldMap;

/**
 * Created by gjasinski on 04.11.16.
 */

public class RectangularMap extends AbstractWorldMap{
    private Position mapSize;

    public RectangularMap(int width, int height){
        mapSize = new Position(width, height);
    }


    /**
     * Indicates if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    public boolean canMoveTo(Position position){
        if(position.x > mapSize.x || position.x < 0 || position.y > mapSize.y || position.y < 0){
            return false;
        }
        return !isOccupied(position);
    }


    /**
     * Returns true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the car
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    public boolean isOccupied(Position position){
        for (Car car: cars) {
            if(car.getPosition().equals(position)) return true;
        }
        return false;
    }

    /**
     * Return object at given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    public Object objectAt(Position position){
        for (Car car: cars){
            if (car.getPosition().equals(position)) return car;
        }
        return null;
    }
}
