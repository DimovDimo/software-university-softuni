package E05_BorderControl;

public class Robot implements Detainable {
    private String model;
    private String id;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    @Override
    public void dataine(String endOfId) {
        if (this.id.endsWith(endOfId)){
            System.out.println(this.id);
        }
    }
}
