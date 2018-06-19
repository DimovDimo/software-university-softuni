package p01_ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getSurfaceArea(){
//        Surface Area = 2lw + 2lh + 2wh
        return 2 * (this.length * this.width + this.length * this.height + this.width * this.height);
    }

    public double getLateralSurfaceAre(){
//        Lateral Surface Area = 2lh + 2wh
        return 2 * (this.length * this.height + this.width * this.height);
    }

    public double getVolume(){
//        Volume = lwh
        return this.length * this.width * this.height;
    }
}
