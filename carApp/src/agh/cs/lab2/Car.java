package agh.cs.lab2;

import agh.cs.lab4.IWorldMap;

/**
 * Created by gjasinski on 04.11.16.
 */
public class Car {
    private Position position;
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
    }

    public Car(IWorldMap map, int x, int y){
        this.position = new Position(x,y);
        this.md = MapDirection.North;
        this.iMap = map;
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
        switch (direction){
            case Forward:
                if(this.iMap.canMoveTo(position.add(getVector(this.md)))) {
                    this.position = position.add(getVector(this.md));
                }
                break;
            case Backward:
                if(this.iMap.canMoveTo(position.add(getVector(this.md)))){
                    this.position = position.add(getVector(this.md).reverse());
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


    public Position getPosition(){
        return this.position;
    }

}