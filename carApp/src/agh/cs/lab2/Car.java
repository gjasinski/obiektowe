package agh.cs.lab2;

import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab6.AbstractWorldMapElement;
import agh.cs.lab7.IPositionChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjasinski on 04.11.16.
 */
public class Car extends AbstractWorldMapElement{
    private List<IPositionChangeListener> listeners =  new ArrayList<>();
    private MapDirection md;
    private IWorldMap iMap;

    public Car(){
        this.position = new Position(2,2);
        this.md = MapDirection.North;
    }

    public Car(IWorldMap map){
        this.position = new Position(2,2);
        this.md = MapDirection.North;
        this.iMap = map;
        addListener((AbstractWorldMap)map); //Is it correct?
    }

    public Car(IWorldMap map, int x, int y){
        this.position = new Position(x,y);
        this.md = MapDirection.North;
        this.iMap = map;
        addListener((AbstractWorldMap)map); //Is it correct?
    }

    @Override
    public String toString(){
        switch (this.md){
            case North:
                return "^";
            case South:
                return "v";
            case West:
                return "<";
            case East:
                return ">";
            default:
                return "Niezaimplementowany przypadek";
        }
    }

    private Position getVector(MapDirection direction){
        int step = 1;
        switch (direction){
            case North:
                return new Position(0,step);
            case South:
                return new Position(0,-step);
            case East:
                return new Position(step,0);
            case West:
                return new Position(-step,0);
            default:
                return new Position(0,0);
        }
    }

    public void move(MoveDirection direction){
        Position oldPosition;
        switch (direction){
            case Forward:
                if(this.iMap.canMoveTo(position.add(getVector(this.md)))) {
                    oldPosition = this.position;
                    this.position = position.add(getVector(this.md));
                    positionChanged(oldPosition, this.position);
                }
                break;
            case Backward:
                if(this.iMap.canMoveTo(position.add(getVector(this.md)))){
                    oldPosition = this.position;
                    this.position = position.add(getVector(this.md).reverse());
                    positionChanged(oldPosition, this.position);
                }
                break;
            case Left:
                this.md = this.md.previous();
                break;
            case Right:
                this.md = this.md.next();
                break;
            default:
                System.out.println("Niezaimplementowany przypadek");
                break;
        }
    }

    private void addListener(IPositionChangeListener listener){
        listeners.add(listener);
    }

    //It's not used, since we don't destroy cars.
    public void removeListener(IPositionChangeListener listener){
        listeners.remove(listeners.indexOf(listener));
    }

    private void positionChanged(Position oldPosition, Position newPosition){
        for (IPositionChangeListener listener: listeners) {
            listener.positionChanged(oldPosition, newPosition);
        }

    }


}