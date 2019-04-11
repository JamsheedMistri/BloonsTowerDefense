package edu.oregonstate.pongbros;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BloonTest {
    @Test
    public void testRun(){
        Bloon bloon = new Bloon("red");
        assertEquals(1, bloon.layers);
    }
}
