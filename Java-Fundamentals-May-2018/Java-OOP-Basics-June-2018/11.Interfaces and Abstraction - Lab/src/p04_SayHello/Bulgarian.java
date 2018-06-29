package p04_SayHello;

public class Bulgarian implements Person {
    private String name;

    public Bulgarian(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String sayHello() {
        System.out.println("Здравей");
        return null;
    }
}
