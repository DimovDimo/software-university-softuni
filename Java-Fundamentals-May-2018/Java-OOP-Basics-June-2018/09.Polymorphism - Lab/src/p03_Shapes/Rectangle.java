package p03_Shapes;

public class Rectangle extends Shape {
    private Double width;
    private Double height;

    public Rectangle(Double width, Double height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public Double getWidth() {
        return width;
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    @Override
    protected void calculatePerimeter() {
        super.setPerimeter( 2 * (this.height + this.width));
    }

    @Override
    protected void calculateArea() {
        super.setArea(this.width * this.height);
    }
}
