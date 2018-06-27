package p02_MethodOverriding;

public class Rectangle {
    private double sideA;
    private double sideB;

    protected Rectangle(double sideA) {
        this.sideA = sideA;
    }

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double area(){
        return this.sideA * this.sideB;
    }

    protected double getSideA() {
        return sideA;
    }
}
