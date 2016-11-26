package test.agh.cs;

import static org.junit.Assert.*;

import agh.cs.lab2.MapDirection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gjasinski on 13.11.16.
 */
public class MapDirectionTest {

    @Test
    public void nextTest(){
        Assert.assertEquals(MapDirection.North.next(),MapDirection.East);
        assertEquals(MapDirection.East.next(),MapDirection.South);
        assertEquals(MapDirection.South.next(),MapDirection.West);
        assertEquals(MapDirection.West.next(),MapDirection.North);
    }

    @Test
    public void previousTest(){
        assertEquals(MapDirection.North.previous(),MapDirection.West);
        assertEquals(MapDirection.East.previous(),MapDirection.North);
        assertEquals(MapDirection.South.previous(),MapDirection.East);
        assertEquals(MapDirection.West.previous(),MapDirection.South);
    }
}