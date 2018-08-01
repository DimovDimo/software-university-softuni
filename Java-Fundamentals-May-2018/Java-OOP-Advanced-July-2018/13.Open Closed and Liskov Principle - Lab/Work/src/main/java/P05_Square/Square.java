package P05_Square;

public class Square extends Rectangle {
    @Override
    public void setWidth(int width){
        super.m_width = width;
        super.m_height = width;
    }

    @Override
    public void setHeight(int height){
        super.m_width = height;
        super.m_height = height;
    }
}
