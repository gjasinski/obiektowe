package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gjasinski on 13.11.16.
 */
public class PositionTest {
    @Test
    public void testToString(){
        assertEquals("(1,2)", new Position(1,2).toString());
    }

    @Test
    public void testSmaller(){
        assertTrue(new Position(1,2).smaller(new Position(3,4)));
        assertFalse(new Position(10,29).smaller(new Position(3,4)));
    }


    @Test
    public void testLarger(){
        assertFalse(new Position(1,2).larger(new Position(3,4)));
        assertTrue(new Position(10,29).larger(new Position(3,4)));
    }

    @Test
    public void testEquals(){
        assertTrue(new Position(1,2).equals(new Position(1,2)));
        assertFalse(new Position(1,2).equals(new Position(1,3)));
    }
    @Test
    public void testAdd(){
        assertEquals("(5,10)", new Position(3,5).add(new Position(2, 5)).toString());
        assertTrue(new Position(3,5).add(new Position(2, 5)).equals(new Position(5, 10)));
        assertFalse(new Position(1,5).add(new Position(2, 5)).equals(new Position(5, 10)));
    }


}