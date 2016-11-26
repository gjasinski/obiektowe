package agh.cs.lab2;

/**
 * Created by Grzegorz Jasinski on 04.11.16.
 */


import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.HayStack;
import agh.cs.lab5.UnboundedMap;

import java.util.*;

import static java.lang.System.out;

public class CarSystem {

    public static void main(String[] args) {

        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            MapVisualizer mapVisualizer = new MapVisualizer();

            //IWorldMap map = new RectangularMap(10, 5);


            LinkedList<HayStack> stacks = new LinkedList<>();
            stacks.add(new HayStack(new Position(-4, -4)));
            stacks.add(new HayStack(new Position(7, 7)));
            stacks.add(new HayStack(new Position(3, 6)));
            stacks.add(new HayStack(new Position(2, 0)));
            IWorldMap map = new UnboundedMap(stacks);

            map.add(new Car(map));
            map.add(new Car(map, 3, 4));

            System.out.println(mapVisualizer.dump(map, new Position(-10, -10), new Position(10, 10)));
            map.run(directions);
            System.out.println(mapVisualizer.dump(map, new Position(-10, -10), new Position(10, 10)));
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.toString());
        }
    }

}
