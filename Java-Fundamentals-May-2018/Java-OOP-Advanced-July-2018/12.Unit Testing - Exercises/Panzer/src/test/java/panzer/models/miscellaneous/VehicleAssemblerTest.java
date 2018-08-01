package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class VehicleAssemblerTest {

    private Assembler vehicleAssembler;

    private AttackModifyingPart attackModifyingPart;

    private DefenseModifyingPart defenseModifyingPart;

    private HitPointsModifyingPart hitPointsModifyingPart;

    @Before
    public void setUp() throws Exception {
        this.vehicleAssembler = new VehicleAssembler();
        this.attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        this.defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        this.hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);

        this.vehicleAssembler.addArsenalPart(this.attackModifyingPart);
        this.vehicleAssembler.addShellPart(this.defenseModifyingPart);
        this.vehicleAssembler.addEndurancePart(this.hitPointsModifyingPart);
    }

    @Test
    public void getTotalWeight() {
        Mockito.when(this.attackModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.defenseModifyingPart.getWeight()).thenReturn(20.0);
        Mockito.when(this.hitPointsModifyingPart.getWeight()).thenReturn(30.0);

        double totalWeight = this.vehicleAssembler.getTotalWeight();
        double expectedTotalWeight = 60.0;

        Assert.assertEquals(expectedTotalWeight, totalWeight, 0.1);
    }

    @Test
    public void getTotalPrice() {
        // Arrange
        Mockito.when(attackModifyingPart.getPrice()).thenReturn(BigDecimal.ZERO);
        Mockito.when(defenseModifyingPart.getPrice()).thenReturn(BigDecimal.ONE);
        Mockito.when(hitPointsModifyingPart.getPrice()).thenReturn(BigDecimal.TEN);

        // Act
        BigDecimal actualTotalPrice = this.vehicleAssembler.getTotalPrice();
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(11);

        // Assert
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void getTotalAttackModification() {
        //Arrange
        Mockito.when(this.attackModifyingPart.getAttackModifier())
.thenReturn(50);
        AttackModifyingPart attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(attackModifyingPart.getAttackModifier()).thenReturn(120);
        this.vehicleAssembler.addArsenalPart(attackModifyingPart);

        //Act
        long actualTotalAttackModification = this.vehicleAssembler.getTotalAttackModification();
        long expectedTotalAttackModification = 170;

        //Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void getTotalDefenseModification() {
        //Arrange
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(50);
        DefenseModifyingPart defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(defenseModifyingPart.getDefenseModifier()).thenReturn(120);
        this.vehicleAssembler.addShellPart(defenseModifyingPart);

        //Act
        long actualTotalDefenseModification = this.vehicleAssembler.getTotalDefenseModification();
        long expectedTotalDefenseModification = 170;

        //Assert
        Assert.assertEquals(expectedTotalDefenseModification, actualTotalDefenseModification);
    }

    @Test
    public void getTotalHitPointModification() {
        //Arrange
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(50);
        HitPointsModifyingPart hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(hitPointsModifyingPart.getHitPointsModifier()).thenReturn(120);
        this.vehicleAssembler.addEndurancePart(hitPointsModifyingPart);

        //Act
        long actualTotalHitPointModification = this.vehicleAssembler.getTotalHitPointModification();
        long expectedTotalHitPointModification = 170;

        //Assert
        Assert.assertEquals(expectedTotalHitPointModification, actualTotalHitPointModification);
    }

    @Test
    public void addArsenalPart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        AttackModifyingPart part = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part1 = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(part.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getAttackModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addArsenalPart(part);
        assembler.addArsenalPart(part1);

        long actualTotalAttackModification = assembler.getTotalAttackModification();
        long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void addShellPart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        DefenseModifyingPart part = Mockito.mock(DefenseModifyingPart.class);
        DefenseModifyingPart part1 = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(part.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addShellPart(part);
        assembler.addShellPart(part1);

        long actualTotalAttackModification = assembler.getTotalDefenseModification();
        long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void addEndurancePart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        HitPointsModifyingPart part = Mockito.mock(HitPointsModifyingPart.class);
        HitPointsModifyingPart part1 = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(part.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(part1.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);

        // Act
        assembler.addEndurancePart(part);
        assembler.addEndurancePart(part1);

        long actualTotalAttackModification = assembler.getTotalHitPointModification();
        long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }
}