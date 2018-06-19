package p02_ClassBoxDataValidation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        if (length <= 0){
            throw new IllegalArgumentException("Length cannot be zero or negative. ");
        }

        this.length = length;
    }

    private void setWidth(double width) {
        if (width <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative. ");
        }

        this.width = width;
    }

    private void setHeight(double height) {
        if (height <= 0){
            throw new IllegalArgumentException("Height cannot be zero or negative. ");
        }

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
