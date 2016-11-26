package test.agh.cs;

import agh.cs.lab2.Car;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.OptionsParser;
import agh.cs.lab2.Position;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.UnboundedMap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Grzegorz Jasinski on 13.11.16.
 */

public class RectangularMapTest {
    @Test
    public void canMoveTo() throws Exception {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        rectangularMap.add(new Car(rectangularMap, 2, 2));

        assertFalse(rectangularMap.canMoveTo(new Position(6,2)));
        assertFalse(rectangularMap.canMoveTo(new Position(2,6)));
        assertFalse(rectangularMap.canMoveTo(new Position(-1,2)));
        assertFalse(rectangularMap.canMoveTo(new Position(2,-1)));
        assertFalse(rectangularMap.canMoveTo(new Position(2,2)));
        assertTrue(rectangularMap.canMoveTo(new Position(3,3)));
    }

    @Test
    public void run() throws Exception {
        String [] s1 = new String [1];
        s1[0]="f b r l f f r r f f f f f f f f";
        MoveDirection[] directions = OptionsParser.parse(s1);
        IWorldMap map = new RectangularMap(10, 5);
        Car car1 = new Car(map);
        Car car2 = new Car(map,3,4);
        map.add(car1);
        map.add(car2);
        map.run(directions);
        System.out.print(car1.getPosition());
        assertTrue(new Position(2,0).equals(car1.getPosition()));
        assertTrue(new Position(3,5).equals(car2.getPosition()));
    }

    @Test
    public void isOccupied() throws Exception {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        assertFalse(rectangularMap.isOccupied(new Position(2,2)));
        rectangularMap.add(new Car(rectangularMap, 2, 2));
        assertTrue(rectangularMap.isOccupied(new Position(2,2)));
    }

}