package agh.cs.lab2;

/**
 * Created by gjasinski on 04.11.16.
 */

public class Position {
    public final int x,y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+ this.x + "," + this.y + ")";
    }

    public boolean smaller(Position position) {
        // TODO Auto-generated method stub
        return this.x < position.x && this.y < position.y;
    }

    public boolean larger(Position position) {
        // TODO Auto-generated method stub
        return this.x > position.x && this.y > position.y;
    }

    public Position add(Position position){
        Position result = new Position(this.x + position.x, this.y + position.y);
        return result;
    }

    public Position reverse(){
        return new Position(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Position))
            return false;
        Position that = (Position) other;
        return this.x == that.x && this.y == that.y;
    }
}
