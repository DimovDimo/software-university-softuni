package E05_BorderControl;

public class Citizen implements Detainable {
    private String name;
    private String age;
    private String id;

    public Citizen(String name, String age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public void dataine(String endOfId) {
        if (this.id.endsWith(endOfId)){
            System.out.println(this.id);
        }
    }
}
