package p08_PokemonTrainer;

public class Pokemon {
    private String name;
    private String element;
    private long healt;

    public Pokemon(String name, String element, long healt) {
        this.name = name;
        this.element = element;
        this.healt = healt;
    }

    public void setHealt(long healt) {
        this.healt = healt;
    }

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public long getHealt() {
        return healt;
    }
}
