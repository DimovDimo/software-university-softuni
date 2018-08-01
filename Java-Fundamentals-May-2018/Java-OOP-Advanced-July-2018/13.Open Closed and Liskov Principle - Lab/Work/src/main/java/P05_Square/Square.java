package P05_Square;

public class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

//    public void setSite(int site){
//        super.setWidth(site);
//        super.setHeight(site);
//    }
}
