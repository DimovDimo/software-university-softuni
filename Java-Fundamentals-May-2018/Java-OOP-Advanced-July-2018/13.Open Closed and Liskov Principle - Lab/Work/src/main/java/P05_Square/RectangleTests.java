package P05_Square;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {
    @Test
    public void getSidesTest() {
        Rectangle rect = new Rectangle();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(10, rect.getWidth());
        Assert.assertEquals(5, rect.getHeight());
    }

    @Test
    public void getAreaTest() {
        Rectangle rect = new Rectangle();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(50, rect.getArea());
    }

    @Test
    public void getSidesSquareTest() {
        Rectangle square = new Square();
        square.setHeight(5);
        Assert.assertEquals(5, square.getWidth());
        Assert.assertEquals(5, square.getHeight());
    }

    @Test
    public void getAreaSquareTest() {
        Rectangle square = new Square();
        square.setWidth(5);
        Assert.assertEquals(25, square.getArea());
    }

    @Test
    public void getSidesSquareSubstitutionTest() {
        Rectangle rect = new Square();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(10, rect.getWidth());
        Assert.assertEquals(10, rect.getHeight());
    }

    @Test
    public void getAreaSquareSubstitutionTest() {
        Rectangle rect = new Square();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(100, rect.getArea());
    }
}
