package C03_CoffeeMachine;

import C03_CoffeeMachine.Enumerations.CoffeeSize;
import C03_CoffeeMachine.Enumerations.CoffeeType;
import C03_CoffeeMachine.Enumerations.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    private List<Coffee> soldCoffees;
    private int coins;

    public CoffeeMachine() {
        this.soldCoffees = new ArrayList<>();
        this.coins = 0;
    }

    public void buyCoffee(String size, String type){
        CoffeeSize coffeeSize = Enum.valueOf(CoffeeSize.class, size.toUpperCase());
        CoffeeType coffeeType = Enum.valueOf(CoffeeType.class, type.toUpperCase());
        if (coffeeSize.getPrice() <= this.coins){

            this.soldCoffees.add(new Coffee(coffeeSize, coffeeType));
            this.coins = 0;
        }
    }

    public void insertCoin(String coin){
        Coin coinInsert = Enum.valueOf(Coin.class, coin.toUpperCase());
        switch (coinInsert){
            case ONE:
                this.coins += Coin.ONE.getValue();
                break;
            case TWO:
                this.coins += Coin.TWO.getValue();
                break;
            case FIVE:
                this.coins += Coin.FIVE.getValue();
                break;
            case TEN:
                this.coins += Coin.TEN.getValue();
                break;
            case TWENTY:
                this.coins += Coin.TWENTY.getValue();
                break;
            case FIFTY:
                this.coins += Coin.FIFTY.getValue();
                break;
        }
    }

    public Iterable<Coffee> coffeesSold(){
        return this.soldCoffees;
    }
}
