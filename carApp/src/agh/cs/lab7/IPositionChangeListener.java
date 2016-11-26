package agh.cs.lab7;

import agh.cs.lab2.Position;

/**
 * Created by Grzegorz Jasinski on 18.11.16.
 */
public interface IPositionChangeListener {
    void positionChanged(Position oldPosition, Position newPosition);
}
