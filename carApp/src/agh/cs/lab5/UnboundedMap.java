package agh.cs.lab5;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Grzegorz Jasinski on 13.11.16.
 */
public class UnboundedMap extends AbstractWorldMap {
    //private List<HayStack> stacks = new ArrayList<>();
    protected Map<Integer, HayStack> stacks = new HashMap<>();

    public UnboundedMap(int quantityOfHayStacks){
        add(new HayStack(new Position(-4, -4)));
        add(new HayStack(new Position(7, 7)));
        add(new HayStack(new Position(3, 6)));
        add(new HayStack(new Position(2, 0)));
    }

    /**
     * Indicates if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    public boolean canMoveTo(Position position){
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
        /*for (Car car: cars) {
            if(car.getPosition().equals(position)) return true;
        }
        for (HayStack hayStack: stacks) {
            if(hayStack.getPosition().equals(position)) return true;
        }
        return false;*/
        if(cars.get(position.hashCode()) != null){
            return true;
        }
        return stacks.get(position.hashCode()) != null;
    }

    /**
     * Add a car the map.
     *
     * @param stack
     *            The car to add.
     * @return True if the car was added.
     */
    public void add(HayStack stack){
        if(canMoveTo(stack.getPosition())){
                stacks.put(stack.getPosition().hashCode(), stack);
            }
            else{
                throw new IllegalArgumentException("UnboundedMap.add - This field is occupied");
            }
    }

    /**
     * Return object at given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    public Object objectAt(Position position){
        /*for (Car car: cars){
            if (car.getPosition().equals(position)) return car;
        }
        for (HayStack hayStack: stacks) {
            if(hayStack.getPosition().equals(position)) return hayStack;
        }
        return null;*/
        if (cars.get(position.hashCode()) != null){
            return cars.get(position.hashCode());
        }
        return stacks.get(position.hashCode());
    }
}
