package p01_ShapesDrawing;

public class Rectangle implements Drawable{
    private Integer widht;
    private Integer height;

    public Rectangle(Integer widht, Integer height) {
        this.widht = widht;
        this.height = height;
    }

    private Integer getWidth() {
        return widht;
    }

    private Integer getHeight() {
        return height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.getHeight(); i++) {
            System.out.print("*");
            for (int j = 1; j < this.getWidth() - 1; j++) {
                System.out.print(" ");
                if (i == 0 || i == (this.getHeight() - 1)){
                    System.out.print("*");
                } else { System.out.print(" "); }}
            System.out.print(" ");
            System.out.print("*");
            System.out.println();
        }
    }
}
