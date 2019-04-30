package entities.factories;

import entities.factories.interfaces.ItemFactory;
import entities.items.*;
import entities.items.interfaces.Item;

import java.lang.reflect.Constructor;

public class ItemWorkshop implements ItemFactory {
    @Override
    public Item createItem(String type) {

//        Object object = null;
//        try {
//            Class<?> clazz = Class.forName(type);
//            Constructor<?>[] ctor = clazz.getDeclaredConstructors();
//            object = ctor[0].newInstance(new Object[] {});
//        } catch (Exception ignored) {
//            ;
//        }
//
//        return (Item) object;

        Item item = null;

        switch (type){
            case "CellPhone": item = new CellPhone(); break;
            case "Colombian": item = new Colombian(); break;
            case "Jewelery": item = new Jewelery(); break;
            case "Laptop": item = new Laptop(); break;
            case "Toothbrush": item = new Toothbrush(); break;
            case "TravelKit": item = new TravelKit(); break;
        }

        return item;
    }
}
