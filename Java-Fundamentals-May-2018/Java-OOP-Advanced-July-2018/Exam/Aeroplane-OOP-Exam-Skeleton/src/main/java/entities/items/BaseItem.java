package entities.items;

import entities.items.interfaces.Item;

public class BaseItem implements Item {

    public BaseItem(int value){

    }

    @Override
    public int getValue() {
        return 0;
    }
}
