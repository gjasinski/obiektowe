package agh.cs.lab5;

import agh.cs.lab2.Position;

/**
 * Created by gjasinski on 10.11.16.
 */
public class HayStack {
    private Position position;

    public HayStack(Position position){
        this.position = position;
    }

    Position getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "s";
    }

}
