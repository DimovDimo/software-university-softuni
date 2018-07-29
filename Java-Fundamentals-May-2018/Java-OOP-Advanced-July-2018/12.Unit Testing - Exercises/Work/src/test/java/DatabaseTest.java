import A01_Database.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class DatabaseTest {
    private static final int MAX_CAPACITY = 16;
    private static final int OVER_MAX_CAPACITY = MAX_CAPACITY * 2;
    private static final int UNDER_MAX_CAPACITY = Math.max(0, MAX_CAPACITY - 1);
    private static final Integer ELEMENT_FOR_ADD = 16;
    private static final Integer[] TOKENS = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, null};
    private static final Integer EXPECTED_ELEMENT_AFTER_REMOVE = 14;
    private static final Integer[] EXPECTED_ELEMENTS_AFTER_FETCH = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private Database database;

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        this.database = new Database(TOKENS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorWithOverMaxCapacity() throws OperationNotSupportedException {
        Integer[] array = new Integer[OVER_MAX_CAPACITY];
        new Database(array);
    }

    @Test
    public void constructorWithMaxCapacity() throws OperationNotSupportedException {
        Integer[] array = new Integer[MAX_CAPACITY];
        new Database(array);
    }

    @Test
    public void constructorWithUnderMaxCapacity() throws OperationNotSupportedException {
        Integer[] array = new Integer[UNDER_MAX_CAPACITY];
        new Database(array);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addNull() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addMoreThanMaxCapacity() throws OperationNotSupportedException {
        this.database.add(ELEMENT_FOR_ADD);
        this.database.add(ELEMENT_FOR_ADD);
    }

    @Test
    public void removeAndReturnLastElemnet() throws OperationNotSupportedException {
        Integer currentElement = this.database.remove();
        Assert.assertEquals(EXPECTED_ELEMENT_AFTER_REMOVE, currentElement, 0.1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeInEmptyDatabase() throws OperationNotSupportedException {
        Integer[] array = new Integer[MAX_CAPACITY];
        Database emptyDatabase = new Database(array);
        emptyDatabase.remove();
    }

    @Test
    public void checkIsEmpty() throws OperationNotSupportedException {
        Integer[] array = new Integer[MAX_CAPACITY];
        Database emptyDatabase = new Database(array);
        Assert.assertTrue(emptyDatabase.isEmpty());
    }

//    @Test
//    public void fetchElements() throws OperationNotSupportedException {
//        Integer[] fetchedElements = this.database.fetch();
//        Assert.assertTrue(Arrays.equals(EXPECTED_ELEMENTS_AFTER_FETCH, fetchedElements));
//    }
}
