package hell.entities.miscellaneous;

import hell.entities.miscellaneous.items.CommonItem;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.*;

public class HeroInventoryTest {

    private static final int VALUE = Integer.MAX_VALUE;
    private static final String STRENGTH_MESSAGE = "Strength is not correct";
    private static final String AGILITY_MESSAGE = "Agility is not correct";
    private static final String DAMAGE_MESSAGE = "Damage is not correct";
    private static final String HIT_POINTS_MESSAGE = "HitPoints is not correct";
    private static final String INTELLIGENCE_MESSAGE = "Intelligence is not correct";

    private Inventory inventory;

    private int nameId;

    @Before
    public void setUp() throws Exception {
        this.inventory = new HeroInventory();
    }

    private Item createComonItemMock(){
        Item commonItem = Mockito.mock(Item.class);

        Mockito.when(commonItem.getName()).thenReturn(String.valueOf(this.nameId++));
        Mockito.when(commonItem.getStrengthBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getAgilityBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getDamageBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getHitPointsBonus()).thenReturn(VALUE);
        Mockito.when(commonItem.getIntelligenceBonus()).thenReturn(VALUE);

        return commonItem;
    }

    private void seeedCommonItems(){
        this.inventory.addCommonItem(this.createComonItemMock());
        this.inventory.addCommonItem(this.createComonItemMock());
        this.inventory.addCommonItem(this.createComonItemMock());
    }

    @Test
    public void getTotalStrengthBonus() {
        // Arrange
        this.seeedCommonItems();

        // Act
        long actualTotalStrengthBonus = this.inventory.getTotalStrengthBonus();
        long expectedTotalStrengthBonus = VALUE * 3L;

        // Assert
        Assert.assertEquals(STRENGTH_MESSAGE, expectedTotalStrengthBonus, actualTotalStrengthBonus);
    }

    @Test
    public void getTotalAgilityBonus() {
        // Arrange
        this.seeedCommonItems();

        // Act
        long actualTotalAgilityBonus = this.inventory.getTotalAgilityBonus();
        long expectedTotalAgilityBonus = VALUE * 3L;

        // Assert
        Assert.assertEquals(AGILITY_MESSAGE, expectedTotalAgilityBonus, actualTotalAgilityBonus);
    }

    @Test
    public void getTotalIntelligenceBonus() {
        // Arrange
        this.seeedCommonItems();

        // Act
        long actualTotalIntelligenceBonus = this.inventory.getTotalIntelligenceBonus();
        long expectedTotalIntelligenceBonus = VALUE * 3L;

        // Assert
        Assert.assertEquals(INTELLIGENCE_MESSAGE, expectedTotalIntelligenceBonus, actualTotalIntelligenceBonus);
    }

    @Test
    public void getTotalHitPointsBonus() {
        // Arrange
        this.seeedCommonItems();

        // Act
        long actualTotalHitPointsBonus = this.inventory.getTotalHitPointsBonus();
        long expectedTotalHitPointsBonus = VALUE * 3L;

        // Assert
        Assert.assertEquals(HIT_POINTS_MESSAGE, expectedTotalHitPointsBonus, actualTotalHitPointsBonus);
    }

    @Test
    public void getTotalDamageBonus() {
        // Arrange
        this.seeedCommonItems();

        // Act
        long actualTotalDamageBonus = this.inventory.getTotalDamageBonus();
        long expectedTotalDamageBonus = VALUE * 3L;

        // Assert
        Assert.assertEquals(DAMAGE_MESSAGE, expectedTotalDamageBonus, actualTotalDamageBonus);
    }

    @Test
    public void addCommonItem() {
        // Arrange
        CommonItem commonItemMock = Mockito.mock(CommonItem.class);

        // Act
        this.inventory.addCommonItem(commonItemMock);

        int actualCountOfCommonItem = 0;

        try {
            Field commonItemField = this.inventory.getClass()
                    .getDeclaredField("commonItems");
            commonItemField.setAccessible(true);
            Map<String, Recipe> mapCommonItem = (Map<String, Recipe>) commonItemField.get(this.inventory);
            actualCountOfCommonItem = mapCommonItem.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        int expextedCountOfCommonItem = 1;

        // Assert
        Assert.assertEquals(expextedCountOfCommonItem, actualCountOfCommonItem);
    }

    @Test
    public void addRecipeItem() {
        // Arrange
        Recipe recipeMock = Mockito.mock(Recipe.class);

        // Act
        this.inventory.addRecipeItem(recipeMock);

        int actualCountOfRecipe = 0;

        try {
            Field recipeField = this.inventory.getClass()
                    .getDeclaredField("recipeItems");
            recipeField.setAccessible(true);
            Map<String, Recipe> recipes = (Map<String, Recipe>) recipeField.get(this.inventory);
            actualCountOfRecipe = recipes.size();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        int expextedCountOfRecipe = 1;

        // Assert
        Assert.assertEquals(expextedCountOfRecipe, actualCountOfRecipe);
    }
}