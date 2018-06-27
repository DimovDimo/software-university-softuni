package p03_Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.setRadius(radius);
    }

    public final Double getRadius() {
        return radius;
    }

    private void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculatePerimeter() {
        super.setPerimeter(2 * Math.PI * this.radius);
    }

    @Override
    protected void calculateArea() {
        super.setArea(Math.PI * this.radius * this.radius);
    }
}
