package agh.cs.lab5;

import agh.cs.lab2.Position;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Grzegorz Jasinski on 13.11.16.
 */
public class UnboundedMap extends AbstractWorldMap {

    public UnboundedMap(LinkedList<HayStack> stacks){
        for (HayStack stack: stacks) {
            this.add(stack);
        }
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
}
