package agh.cs.lab6;

import agh.cs.lab2.Position;

/**
 * Created by Grzegorz Jasinski on 18.11.16.
 */
public class AbstractWorldMapElement implements IMapElement{
    protected Position position;

    public Position getPosition(){
        return this.position;
    }
}
