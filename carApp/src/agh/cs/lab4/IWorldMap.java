package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Position;
import agh.cs.lab2.Car;
import agh.cs.lab5.HayStack;

/**
 * Interface responsible for interacting with the map of the world.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicates if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Position position);

    /**
     * Add a car the map.
     *
     * @param car
     *            The car to add.
     * @return void
     */
    void add(Car car);


    /**
     * Move the mapElement on the map according to the provided move directions. Every
     * n-th direction should be sent the n-th car on the map.
     *
     * @param directions
     *            Array of move directions.
     */
    void run(MoveDirection[] directions);

    /**
     * Returns true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the car
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Position position);

    /**
     * Return object at given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Position position);
}