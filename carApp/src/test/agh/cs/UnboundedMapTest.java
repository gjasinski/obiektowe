package test.agh.cs;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.OptionsParser;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.HayStack;
import agh.cs.lab5.UnboundedMap;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Grzegorz Jasinski on 13.11.16.
 */
public class UnboundedMapTest {
    @Test
    public void canMoveTo() throws Exception {
        UnboundedMap unboundedMap = new UnboundedMap(createStacks());
        unboundedMap.add(new Car(unboundedMap));
        assertTrue(unboundedMap.canMoveTo(new Position(1, 1)));
        assertFalse(unboundedMap.canMoveTo(new Position(2, 2)));
        assertFalse(unboundedMap.canMoveTo(new Position(7, 7)));
        assertTrue(unboundedMap.canMoveTo(new Position(1000,999999)));
    }


    @Test
    public void run() throws Exception {
        String [] s1 = new String [1];
        s1[0]="f b r l f f r r f f f f f f f f";
        MoveDirection[] directions = OptionsParser.parse(s1);
        IWorldMap map = new UnboundedMap(createStacks());
        Car car1 = new Car(map);
        Car car2 = new Car(map,3,4);
        map.add(car1);
        map.add(car2);
        map.run(directions);
        assertTrue(new Position(2,1).equals(car1.getPosition()));
        assertTrue(new Position(3,5).equals(car2.getPosition()));
    }

    @Test
    public void isOccupied() throws Exception {
        UnboundedMap unboundedMap = new UnboundedMap(createStacks());
        assertFalse(unboundedMap.isOccupied(new Position(2, 2)));
        unboundedMap.add(new Car(unboundedMap));
        assertTrue(unboundedMap.isOccupied(new Position(2, 2)));
        assertFalse(unboundedMap.isOccupied(new Position(-7, -7)));
    }

    private LinkedList<HayStack> createStacks(){
        LinkedList<HayStack> stacks = new LinkedList<>();
        stacks.add(new HayStack(new Position(-4, -4)));
        stacks.add(new HayStack(new Position(7, 7)));
        stacks.add(new HayStack(new Position(3, 6)));
        stacks.add(new HayStack(new Position(2, 0)));
        return stacks;
    }
}