import junit.framework.TestCase;

import java.util.Collection;
import java.util.Random;

public class ArrayCollectionTest extends TestCase {

    public void testSizeWhenSizeIsZero() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        int expectedValue = 0;

        int actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

        arrayCollection.add(5);
        arrayCollection.add(5);
        arrayCollection.add(6);

        arrayCollection.remove(5);
        arrayCollection.remove(6);
        arrayCollection.remove(5);

        assertEquals(expectedValue, actualValue);

    }

    public void testSizeWhenSizeIsNotZero() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        int expectedValue = 3;

        arrayCollection.add(5);
        arrayCollection.add(5);
        arrayCollection.add(6);

        int actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

        ArrayCollection<Integer> arrayCollection1 = new ArrayCollection<>();

        for (int i = 0; i < 1000; i++) {
            arrayCollection1.add(i);
        }

        expectedValue = 1000;

        actualValue = arrayCollection1.size();

        assertEquals(expectedValue, actualValue);

    }

    public void testIsEmpty() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        boolean expectedValue = true;

        boolean actualValue = arrayCollection.isEmpty();

        assertEquals(expectedValue, actualValue);
        assertTrue(actualValue);

        arrayCollection.add(5);

        actualValue = arrayCollection.isEmpty();

        assertFalse(actualValue);

        arrayCollection.remove(5);

        actualValue = arrayCollection.isEmpty();

        assertEquals(expectedValue, actualValue);
        assertTrue(actualValue);

    }

    public void testContains() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        boolean expectedValue = false;

        boolean actualValue = arrayCollection.contains(7);

        assertFalse(actualValue);
        assertEquals(expectedValue, actualValue);

        expectedValue = true;

        arrayCollection.add(5);

        actualValue = arrayCollection.contains(5);

        assertTrue(actualValue);
        assertEquals(expectedValue, actualValue);

        expectedValue = false;

        arrayCollection.remove(5);

        actualValue = arrayCollection.contains(5);

        assertFalse(actualValue);
        assertEquals(expectedValue, actualValue);

    }

    public void testIterator() throws Exception {

    }

    public void testToArray() throws Exception {

    }

    public void testToArray1() throws Exception {

    }

    public void testAdd() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testAddAll() throws Exception {

    }

    public void testClear() throws Exception {

    }

    public void testRetainAll() throws Exception {

    }

    public void testRemoveAll() throws Exception {

    }

    public void testContainsAll() throws Exception {

    }

}