package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static final Integer[] EXPECTED_ELEMENTS = {1, 2, 3, 4, 5};
    private static final int EXPECTED_SIZE = EXPECTED_ELEMENTS.length;
    private static final int EXPECTED_INDEX = EXPECTED_ELEMENTS.length - 1;
    private static final Integer EXPECTED_LAST_ELEMENT = EXPECTED_ELEMENTS[3];
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(1, 2, 3, 4, 5);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObject() throws OperationNotSupportedException {


        int expectedSize = EXPECTED_ELEMENTS.length;
        int expectedIndex = expectedSize - 1;


        Integer[] actualElements = database.getElements();
        int actualSize = actualElements.length;
        int actualIndex = actualSize - 1;

        assertArrayEquals(EXPECTED_ELEMENTS, actualElements);
        assertEquals(EXPECTED_SIZE, actualSize);
        assertEquals(EXPECTED_INDEX, actualIndex);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_Elements_Greater_Than16() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_Elements_AreZero() throws OperationNotSupportedException {

        Database database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_Null_ShouldTrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_ShouldAdd_Element_AtLastPosition() throws OperationNotSupportedException {
        database.add(25);

        Integer[] elements = database.getElements();
        int actualSize = elements.length;


        assertEquals(EXPECTED_SIZE + 1, actualSize);
        assertEquals(Integer.valueOf(25), elements[actualSize - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_When_DatabaseEmpty() throws OperationNotSupportedException {
        for (int i = 0; i <= EXPECTED_SIZE; i++) {
            database.remove();
        }
    }

    @Test
    public void test_Remove_ShouldRemove_Successfully() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        Integer actualElement = elements[elements.length - 1];

        assertEquals(EXPECTED_LAST_ELEMENT, actualElement);
    }

    @Test
    public void test_Remove_Should_DecreaseSize_When_RemoveElement() throws OperationNotSupportedException {
        database.remove();

        int actualSize = database.getElements().length;

        assertEquals(EXPECTED_SIZE - 1, actualSize);
    }
}
