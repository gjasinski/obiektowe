package agh.cs.lab5;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.OptionsParser;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gjasinski on 13.11.16.
 */
public class UnboundedMapTest {
    @Test
    public void canMoveTo() throws Exception {
        UnboundedMap unboundedMap = new UnboundedMap(4);
        unboundedMap.add(new Car(unboundedMap));
        assertTrue(unboundedMap.canMoveTo(new Position(1, 1)));
        assertFalse(unboundedMap.canMoveTo(new Position(2, 2)));
        assertFalse(unboundedMap.canMoveTo(new Position(-7, -7)));
        assertTrue(unboundedMap.canMoveTo(new Position(1000,999999)));
    }

    @Test
    public void add() throws Exception {
        UnboundedMap unboundedMap = new UnboundedMap(4);
        assertTrue(unboundedMap.add(new Car(unboundedMap)));
        assertFalse(unboundedMap.add(new Car(unboundedMap)));
        assertFalse(unboundedMap.add(new Car(unboundedMap, -7, -7)));
        assertTrue(unboundedMap.add(new Car(unboundedMap, 100000, 10000)));
    }

    @Test
    public void run() throws Exception {
        String [] s1 = new String [1];
        s1[0]="f b r l f f r r f f f f f f f f";
        MoveDirection[] directions = new OptionsParser().parse(s1);
        IWorldMap map = new RectangularMap(10, 5);
        Car car1 = new Car(map);
        Car car2 = new Car(map,3,4);
        map.add(car1);
        map.add(car2);
        map.run(directions);
        System.out.print(car1.getPosition());
        assertTrue(new Position(2,1).equals(car1.getPosition()));
        assertTrue(new Position(3,5).equals(car2.getPosition()));
    }

    @Test
    public void isOccupied() throws Exception {
        UnboundedMap unboundedMap = new UnboundedMap(4);
        assertFalse(unboundedMap.isOccupied(new Position(2, 2)));
        unboundedMap.add(new Car(unboundedMap));
        assertTrue(unboundedMap.isOccupied(new Position(2, 2)));
        assertFalse(unboundedMap.isOccupied(new Position(-7, -7)));
    }

}