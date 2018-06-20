package p05_RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {
    public Object getRandomElement(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(super.size()) + 1;
        return super.get(randomIndex);
    }
}
