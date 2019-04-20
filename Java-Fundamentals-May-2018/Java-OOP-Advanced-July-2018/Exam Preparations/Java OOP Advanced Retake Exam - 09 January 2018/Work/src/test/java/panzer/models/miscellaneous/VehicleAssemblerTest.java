package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import panzer.contracts.Assembler;
import panzer.contracts.AttackModifyingPart;
import panzer.contracts.DefenseModifyingPart;
import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class VehicleAssemblerTest {

    private static final int VALUE = Integer.MAX_VALUE;

    private Assembler assembler;

    private AttackModifyingPart attackModifyingPart;

    private DefenseModifyingPart defenseModifyingPart;

    private HitPointsModifyingPart hitPointsModifyingPart;

    @Before
    public void setUp() throws Exception {
        assembler = new VehicleAssembler();

        attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);

        assembler.addArsenalPart(attackModifyingPart);
        assembler.addShellPart(defenseModifyingPart);
        assembler.addEndurancePart(hitPointsModifyingPart);
    }

    @Test
    public void getTotalWeight() {
        Mockito.when(attackModifyingPart.getWeight()).thenReturn(2.0);
        Mockito.when(defenseModifyingPart.getWeight()).thenReturn(2.0);
        Mockito.when(hitPointsModifyingPart.getWeight()).thenReturn(2.0);

        double expected = 6.0;
        double actual = assembler.getTotalWeight();
        double delta = 0.1;

        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void getTotalPrice() {
        Mockito.when(attackModifyingPart.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(defenseModifyingPart.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(hitPointsModifyingPart.getPrice()).thenReturn(BigDecimal.TEN);

        BigDecimal expected = BigDecimal.valueOf(30);
        BigDecimal actual = assembler.getTotalPrice();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalAttackModification() {
        Mockito.when(attackModifyingPart.getAttackModifier()).thenReturn(2);

        long expected = 2;
        long actual = assembler.getTotalAttackModification();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalDefenseModification() {
        Mockito.when(defenseModifyingPart.getDefenseModifier()).thenReturn(2);

        long expected = 2;
        long actual = assembler.getTotalDefenseModification();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalHitPointModification() {
        Mockito.when(hitPointsModifyingPart.getHitPointsModifier()).thenReturn(2);

        long expected = 2;
        long actual = assembler.getTotalHitPointModification();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addArsenalPart() {

        AttackModifyingPart attackModifyingPart1 = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart attackModifyingPart2 = Mockito.mock(AttackModifyingPart.class);

        Mockito.when(attackModifyingPart1.getAttackModifier()).thenReturn(VALUE);
        Mockito.when(attackModifyingPart2.getAttackModifier()).thenReturn(VALUE);

        assembler.addArsenalPart(attackModifyingPart1);
        assembler.addArsenalPart(attackModifyingPart2);

        long expected = (long) VALUE * 2;
        long actual = assembler.getTotalAttackModification();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addShellPart() {
        DefenseModifyingPart defenseModifyingPart1 = Mockito.mock(DefenseModifyingPart.class);
        DefenseModifyingPart defenseModifyingPart2 = Mockito.mock(DefenseModifyingPart.class);

        Mockito.when(defenseModifyingPart1.getDefenseModifier()).thenReturn(VALUE);
        Mockito.when(defenseModifyingPart2.getDefenseModifier()).thenReturn(VALUE);

        assembler.addShellPart(defenseModifyingPart1);
        assembler.addShellPart(defenseModifyingPart2);

        long expected = (long) VALUE * 2;
        long actual = assembler.getTotalDefenseModification();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addEndurancePart() {
        HitPointsModifyingPart hitPointsModifyingPart1 = Mockito.mock(HitPointsModifyingPart.class);
        HitPointsModifyingPart hitPointsModifyingPart2 = Mockito.mock(HitPointsModifyingPart.class);

        Mockito.when(hitPointsModifyingPart1.getHitPointsModifier()).thenReturn(VALUE);
        Mockito.when(hitPointsModifyingPart2.getHitPointsModifier()).thenReturn(VALUE);

        assembler.addEndurancePart(hitPointsModifyingPart1);
        assembler.addEndurancePart(hitPointsModifyingPart2);

        long expected = (long) VALUE * 2;
        long actual = assembler.getTotalHitPointModification();
    }
}