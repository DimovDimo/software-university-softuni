package hell.entities.miscellaneous;

import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HeroInventoryTest {

    private static final int VALUE = Integer.MAX_VALUE;

    private Inventory inventory;

    @Before
    public void setUp() throws Exception {
        inventory = new HeroInventory();

        this.seedCommonItems();
        this.seedRecipeItem();
    }

    private Item createCommonItemMock() {
        Item commonItem = Mockito.mock(Item.class);

        Mockito.when(commonItem.getStrengthBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getAgilityBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getIntelligenceBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getHitPointsBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getDamageBonus()).thenReturn(VALUE);

        return commonItem;
    }

    private void seedCommonItems() {
        Item commonItemMock1 = this.createCommonItemMock();
        Mockito.when(commonItemMock1.getName()).thenReturn("commonItemMock1");
        Item commonItemMock2 = this.createCommonItemMock();
        Mockito.when(commonItemMock2.getName()).thenReturn("commonItemMock2");
        Item commonItemMock3 = this.createCommonItemMock();
        Mockito.when(commonItemMock3.getName()).thenReturn("commonItemMock3");
        this.inventory.addCommonItem(commonItemMock1);
        this.inventory.addCommonItem(commonItemMock2);
        this.inventory.addCommonItem(commonItemMock3);
    }


    private void seedRecipeItem() {
        Recipe recipeMock = Mockito.mock(Recipe.class);

        List<String> requiredItems = new ArrayList<>();
        requiredItems.add("commonItemMock1");
        requiredItems.add("commonItemMock2");
        requiredItems.add("commonItemMock4");

        Mockito.when(recipeMock.getRequiredItems()).thenReturn(requiredItems);

        this.inventory.addRecipeItem(recipeMock);
    }

    @Test
    public void getTotalStrengthBonus() {
        long actual = inventory.getTotalStrengthBonus();
        long expected = VALUE * 3L;

        Assert.assertEquals("", expected, actual);
    }

    @Test
    public void getTotalAgilityBonus() {
        long actual = inventory.getTotalAgilityBonus();
        long expected = VALUE * 3L;

        Assert.assertEquals("", expected, actual);
    }

    @Test
    public void getTotalIntelligenceBonus() {
        long actual = inventory.getTotalIntelligenceBonus();
        long expected = VALUE * 3L;

        Assert.assertEquals("", expected, actual);
    }

    @Test
    public void getTotalHitPointsBonus() {
        long actual = inventory.getTotalHitPointsBonus();
        long expected = VALUE * 3L;

        Assert.assertEquals("", expected, actual);
    }

    @Test
    public void getTotalDamageBonus() {
        long actual = inventory.getTotalDamageBonus();
        long expected = VALUE * 3L;

        Assert.assertEquals("", expected, actual);
    }

    @Test
    public void addCommonItem() {
        // Arrange
        this.seedCommonItems();
        this.seedRecipeItem();
        Item commonItemMock4 = Mockito.mock(Item.class);
        Mockito.when(commonItemMock4.getName()).thenReturn("commonItemMock4");

        // Act
        this.inventory.addCommonItem(commonItemMock4);
        int actualCountOfCommonItems = 0;

        try {
            Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
            commonItemsField.setAccessible(true);
            Map<String, Item> commonItemsMap = (Map<String, Item>) commonItemsField.get(this.inventory);
            actualCountOfCommonItems = commonItemsMap.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        int expectedCountOfCommonItems = 2;

        // Assert
        Assert.assertEquals(expectedCountOfCommonItems, actualCountOfCommonItems);
    }

    @Test
    public void addRecipeItem() {
        // Arrange
        Recipe recipeMock = Mockito.mock(Recipe.class);

        // Act
        this.inventory.addRecipeItem(recipeMock);
        int actualCountOfRecipes = 0;

        try {
            Field recipesField = this.inventory.getClass().getDeclaredField("recipeItems");
            recipesField.setAccessible(true);
            Map<String, Recipe> recipesMap = (Map<String, Recipe>) recipesField.get(this.inventory);
            actualCountOfRecipes = recipesMap.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        int expectedCountOfRecipes = 1;

        // Assert
        Assert.assertEquals(expectedCountOfRecipes, actualCountOfRecipes);
    }
}