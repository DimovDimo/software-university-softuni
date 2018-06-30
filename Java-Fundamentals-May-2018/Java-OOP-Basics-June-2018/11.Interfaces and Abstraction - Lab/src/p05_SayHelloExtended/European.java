package p05_SayHelloExtended;

public class European extends BasePerson {
    public European(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        System.out.println("Hello");
        return null;
    }
}
