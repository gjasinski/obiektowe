package agh.cs.lab5;

import agh.cs.lab2.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz Jasinski on 13.11.16.
 */
public class UnboundedMap extends AbstractWorldMap {
    //private List<HayStack> stacks = new ArrayList<>();
    //protected Map<Integer, HayStack> stacks = new HashMap<>();

    public UnboundedMap(int quantityOfHayStacks){
        this.add(new HayStack(new Position(-4, -4)));
        this.add(new HayStack(new Position(7, 7)));
        this.add(new HayStack(new Position(3, 6)));
        this.add(new HayStack(new Position(2, 0)));
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
        /*for (Car car: mapElement) {
            if(car.getPosition().equals(position)) return true;
        }
        for (HayStack hayStack: stacks) {
            if(hayStack.getPosition().equals(position)) return true;
        }
        return false;*/
        /*if(mapElement.get(position.hashCode()) != null){
            return true;
        }*/
        return mapElement.get(position) != null;
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
                mapElement.put(stack.getPosition(), stack);
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
        /*for (Car car: mapElement){
            if (car.getPosition().equals(position)) return car;
        }
        for (HayStack hayStack: stacks) {
            if(hayStack.getPosition().equals(position)) return hayStack;
        }
        return null;*/
        /*if (mapElement.get(position.hashCode()) != null){
            return mapElement.get(position.hashCode());
        }*/
        return mapElement.get(position);
    }
}
