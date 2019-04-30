package entities.factories.interfaces;

import entities.items.interfaces.Item;

public interface ItemFactory {
    Item createItem(String type);
}
