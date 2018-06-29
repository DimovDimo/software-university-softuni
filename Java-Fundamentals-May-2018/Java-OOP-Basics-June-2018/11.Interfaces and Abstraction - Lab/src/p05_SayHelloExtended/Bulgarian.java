package p05_SayHelloExtended;

public class Bulgarian extends BasePerson {

    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        System.out.println("Здравей");
        return null;
    }
}
