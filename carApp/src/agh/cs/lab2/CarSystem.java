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
        /*
        Car car = new Car();
        ArrayList<MoveDirection> instructions = new ArrayList<MoveDirection>();
        //instructions = OptionsParser.parse(args[1]);
        Scanner sc = new Scanner(System.in);
        instructions = OptionsParser.parse(sc.nextLine());
        sc.close();
        System.out.println(car);
        for(MoveDirection moveDirection : instructions){
            car.move(moveDirection);
        }
        System.out.println(car);
        */
        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            //IWorldMap map = new RectangularMap(10, 5);
            IWorldMap map = new UnboundedMap(4);
            MapVisualizer mapVisualizer = new MapVisualizer();
            map.add(new Car(map));
            map.add(new Car(map, 3, 4));
            System.out.println(mapVisualizer.dump(map, new Position(0, 0), new Position(10, 5)));
            map.run(directions);
            System.out.println(mapVisualizer.dump(map, new Position(0, 0), new Position(10, 5)));
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }

}
