package ru.stqa.pft.sandbox;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class PointTests {

    @Test
    public void first() {
        Point p1 = new Point(-2.0, 5.0 );
        Point p2 = new Point(-7.0, 9.0 );
        assertEquals(p2.distance(p1), 6.4031242374328485);
        System.out.println("Расстояние между точкой p1 и точкой p2 найдено верно: " + 6.4031242374328485 + " = " + 6.4031242374328485);
    }

    @Test
    public void second() {
        Point p1 = new Point(2.0, 7.0 );
        Point p2 = new Point(-9.0, 7.0 );
        assertEquals(p2.distance(p1), 11.0);
        System.out.println("Расстояние между точкой p1 и точкой p2 найдено верно: " + 11 + " = " + 11);
    }

    @Test
    public void third() {
        Point p1 = new Point(8.0, 8.0 );
        Point p2 = new Point(3.0, 8.0 );
        assertEquals(p2.distance(p1), 5.0);
        System.out.println("Расстояние между точкой p1 и точкой p2 найдено верно: " + 5 + " = " + 5);
    }
}