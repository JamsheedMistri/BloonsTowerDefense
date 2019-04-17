package BloonsTowerDefense;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BloonTest {
    @Test
    public void testBloons(){
        Bloon bloon1 = new Bloon("red");
        Bloon bloon2 = new Bloon("blue");
        Bloon bloon3 = new Bloon("green");
        Bloon bloon4 = new Bloon("yellow");
        Bloon bloon5 = new Bloon("pink");
        Bloon bloon6 = new Bloon("cyan");
        Bloon bloon7 = new Bloon("black");
        Bloon bloon8 = new Bloon("clay");
        Bloon bloon9 = new Bloon("lead");

        assertEquals(1, bloon1.layers);
        assertEquals(2, bloon2.layers);
        assertEquals(3, bloon3.layers);
        assertEquals(4, bloon4.layers);
        assertEquals(5, bloon5.layers);
        assertEquals(6, bloon6.layers);
        assertEquals(7, bloon7.layers);
        assertEquals(8, bloon8.layers);
        assertEquals(9, bloon9.layers);

    }


}
