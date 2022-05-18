package codinggame.amazon;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTestt {

    @DisplayName ("S -> S SE SW")
    @Test
    void testS() {
        List<String> s = Direction.closestDirection("S");
        Assertions.assertTrue(s.containsAll(Arrays.asList("S", "SE", "SW")));
        Assertions.assertEquals(3, s.size());
    }

    @DisplayName ("N -> N NE NW")
    @Test
    void testN() {
        List<String> s = Direction.closestDirection("N");
        Assertions.assertTrue(s.containsAll(Arrays.asList("N", "NE", "NW")));
        Assertions.assertEquals(3, s.size());

    }

    @DisplayName ("W -> W SW NW")
    @Test
    void testW() {
        List<String> s = Direction.closestDirection("W");
        Assertions.assertTrue(s.containsAll(Arrays.asList("W", "SW", "NW")));
        Assertions.assertEquals(3, s.size());

    }

    @DisplayName ("E -> E SE NE")
    @Test
    void testE() {
        List<String> s = Direction.closestDirection("E");
        Assertions.assertTrue(s.containsAll(Arrays.asList("E", "SE", "NE")));
        Assertions.assertEquals(3, s.size());

    }

    @DisplayName ("SE -> SE S E")
    @Test
    void testSE() {
        List<String> s = Direction.closestDirection("SE");
        Assertions.assertTrue(s.containsAll(Arrays.asList("SE", "E", "S")));
        Assertions.assertEquals(3, s.size());
    }

    @DisplayName ("NE -> NE N E")
    @Test
    void testNE() {
        List<String> s = Direction.closestDirection("NE");
        Assertions.assertTrue(s.containsAll(Arrays.asList("NE", "N", "E")));
        Assertions.assertEquals(3, s.size());

    }

    @DisplayName ("NW -> NW N W")
    @Test
    void testNW() {
        List<String> s = Direction.closestDirection("NW");
        Assertions.assertTrue(s.containsAll(Arrays.asList("NW", "N", "W")));
        Assertions.assertEquals(3, s.size());

    }

    @DisplayName ("SW -> SW S W")
    @Test
    void testSW() {
        List<String> s = Direction.closestDirection("SW");
        Assertions.assertTrue(s.containsAll(Arrays.asList("SW", "W", "S")));
        Assertions.assertEquals(3, s.size());

    }
}
