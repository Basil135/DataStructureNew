import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.*;

public class ArrayCollectionTest {

    @Test
    public void sizeWhenSizeIsZero() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        int expectedValue = 0;
        int actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void sizeWhenSizeIsNonZero() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(2);
        arrayCollection.add(2);
        arrayCollection.add(2);

        int expectedValue = 3;
        int actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void isEmptyWhenEmpty() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        boolean expectedValue = true;
        boolean actualValue = arrayCollection.isEmpty();

        assertEquals(expectedValue, actualValue);
        assertTrue(arrayCollection.isEmpty());
        assertFalse(!arrayCollection.isEmpty());

    }

    @Test
    public void isEmptyWhenNonEmpty() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(2);
        arrayCollection.add(2);
        arrayCollection.add(2);

        boolean expectedValue = false;
        boolean actualValue = arrayCollection.isEmpty();

        assertEquals(expectedValue, actualValue);
        assertTrue(!arrayCollection.isEmpty());
        assertFalse(arrayCollection.isEmpty());

    }

    @Test
    public void containsWhenArrayIsEmpty() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        boolean expectedValue = false;
        boolean actualValue = arrayCollection.contains(2);

        assertEquals(expectedValue, actualValue);
        assertTrue(!arrayCollection.contains(2));
        assertFalse(arrayCollection.contains(2));

    }

    @Test
    public void containsWhenNoContains() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(1);
        arrayCollection.add(3);
        arrayCollection.add(4);

        boolean expectedValue = false;
        boolean actualValue = arrayCollection.contains(2);

        assertEquals(expectedValue, actualValue);
        assertTrue(!arrayCollection.contains(2));
        assertFalse(arrayCollection.contains(2));

    }

    @Test
    public void containsWhenContains() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(1);
        arrayCollection.add(2);
        arrayCollection.add(3);
        arrayCollection.add(4);

        boolean expectedValue = true;
        boolean actualValue = arrayCollection.contains(2);

        assertEquals(expectedValue, actualValue);
        assertTrue(arrayCollection.contains(2));
        assertFalse(!arrayCollection.contains(2));

    }

    @Test(expected = NullPointerException.class)
    public void containsWhenNullPointerException() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(1);
        arrayCollection.add(3);
        arrayCollection.add(4);

        arrayCollection.contains(null);

    }

    @Test(expected = NullPointerException.class)
    public void toArrayException() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();
        Integer[] a = null;

        arrayCollection.toArray(a);
    }

    @Test(expected = NullPointerException.class)
    public void addWhenException() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(null);

    }

    @Test(expected = NullPointerException.class)
    public void removeException() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        Integer a = null;

        arrayCollection.remove(a);

    }

    @Test
    public void removeWhenIsExist() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(2);
        arrayCollection.add(5);
        arrayCollection.add(7);
        arrayCollection.add(9);

        arrayCollection.remove(7);

        assertFalse(arrayCollection.contains(7));

    }

    @Test
    public void removeWhenNonExist() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        arrayCollection.add(1);
        arrayCollection.add(2);
        arrayCollection.add(3);

        assertFalse(arrayCollection.remove(4));

    }

    @Test
    public void removeFromEmptyCollection() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        assertFalse(arrayCollection.remove(3));

    }

    @Test
    public void containsAll() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        HashSet<Integer> integerHashSet = new HashSet<>();

        boolean expectedValue;
        boolean actualValue;

        integerHashSet.add(1);
        integerHashSet.add(2);
        integerHashSet.add(3);

        arrayCollection.add(4);
        arrayCollection.add(1);
        arrayCollection.add(3);

        expectedValue = false;
        actualValue = arrayCollection.containsAll(integerHashSet);

        assertFalse(arrayCollection.containsAll(integerHashSet));
        assertTrue(!arrayCollection.containsAll(integerHashSet));
        assertEquals(expectedValue, actualValue);

        arrayCollection.add(7);
        arrayCollection.add(2);

        expectedValue = true;
        actualValue = arrayCollection.containsAll(integerHashSet);

        assertFalse(!arrayCollection.containsAll(integerHashSet));
        assertTrue(arrayCollection.containsAll(integerHashSet));
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void addAll() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        HashSet<Integer> integerHashSet = new HashSet<>();

        integerHashSet.add(1);
        integerHashSet.add(2);
        integerHashSet.add(3);

        arrayCollection.add(4);
        arrayCollection.add(1);
        arrayCollection.add(3);

        arrayCollection.addAll(integerHashSet);

        assertTrue(arrayCollection.containsAll(integerHashSet));

    }

    @Test
    public void removeAll() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        HashSet<Integer> integerHashSet = new HashSet<>();

        int expectedValueOne;
        int actualValueOne;

        boolean expectedValueTwo;
        boolean actualValueTwo;

        integerHashSet.add(1);
        integerHashSet.add(2);
        integerHashSet.add(3);

        arrayCollection.add(4);
        arrayCollection.add(1);
        arrayCollection.add(3);

        arrayCollection.removeAll(integerHashSet);

        expectedValueOne = 1;
        actualValueOne = arrayCollection.size();

        expectedValueTwo = true;
        actualValueTwo = arrayCollection.contains(4);

        assertEquals(expectedValueOne, actualValueOne);
        assertEquals(expectedValueTwo, actualValueTwo);

    }

    @Test
    public void retainAll() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        HashSet<Integer> integerHashSet = new HashSet<>();

        int expectedValueOne;
        int actualValueOne;

        boolean expectedValueTwo;
        boolean actualValueTwo;

        integerHashSet.add(1);
        integerHashSet.add(2);
        integerHashSet.add(3);

        arrayCollection.add(4);
        arrayCollection.add(1);
        arrayCollection.add(3);

        arrayCollection.retainAll(integerHashSet);

        expectedValueOne = 2;
        actualValueOne = arrayCollection.size();

        expectedValueTwo = false;
        actualValueTwo = arrayCollection.contains(4);

        assertEquals(expectedValueOne, actualValueOne);
        assertEquals(expectedValueTwo, actualValueTwo);

    }

    @Test
    public void clear() throws Exception {

        ArrayCollection<Integer> arrayCollection = new ArrayCollection<>();

        int expectedValue;
        int actualValue;

        arrayCollection.add(2);
        arrayCollection.add(3);
        arrayCollection.add(2);
        arrayCollection.add(3);

        expectedValue = 4;
        actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

        arrayCollection.clear();

        expectedValue = 0;
        actualValue = arrayCollection.size();

        assertEquals(expectedValue, actualValue);

    }

}