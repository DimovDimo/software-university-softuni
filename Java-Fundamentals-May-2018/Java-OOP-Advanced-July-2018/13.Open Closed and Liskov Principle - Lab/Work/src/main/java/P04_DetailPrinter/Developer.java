package P04_DetailPrinter;

public class Developer extends Employee {
    private Iterable<String> projects;

    private Developer(String name, Iterable<String> documents) {
        super(name);
        this.projects = documents;
    }

    @Override
    public String toString() {
        return super.toString() + " Projects: " + this.projects;
    }
}
