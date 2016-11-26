package agh.cs.lab2;
/**
 * Created by Grzegorz Jasinski on 04.11.16.
 */


import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.UnboundedMap;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import static java.lang.System.out;

public class CarSystem {

    public static void main(String[] args) {

        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            //IWorldMap map = new RectangularMap(10, 5);
            IWorldMap map = new UnboundedMap(4);
            MapVisualizer mapVisualizer = new MapVisualizer();
            map.add(new Car(map));
            map.add(new Car(map, 3, 4));
            System.out.println(mapVisualizer.dump(map, new Position(-10, -10), new Position(10, 10)));
            map.run(directions);
            System.out.println(mapVisualizer.dump(map, new Position(-10, -10), new Position(10, 10)));
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }

}
