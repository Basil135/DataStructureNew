import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {

    public T[] array = (T[])new Object[1];

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        if (array == null || o == null)
            throw new NullPointerException();

        boolean result = false;

        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {

            if (iterator.next().equals(o))
                result = true;

        }

        return result;

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {

        T1[] result = (T1[])new Object[a.length];

        if (array == null)
            throw new NullPointerException();

        if (a.length >= size()) {

            System.arraycopy(array, 0, a, 0, size());

            System.arraycopy(a, 0, result, 0, a.length);

        } else {

            result = (T1[]) new Object[size() + 1];

            System.arraycopy(array, 0, result, 0, size());

            result[size()] = null;

        }

        return result;

    }

    @Override
    public boolean add(T t) {

        if (t == null)
            throw new NullPointerException();

        if (size() < array.length) {

            array[size] = t;

            size++;

        } else {

            array = Arrays.copyOf(array, array.length * 2);

            array[size] = t;

            size++;

        }

        return true;

    }

    @Override
    public boolean remove(Object o) {

        if (o == null)
            throw new NullPointerException();

        boolean result = false;

        int removeFlag = -1;

        for (int count = 0; count < size(); count++) {

            if (array[count].equals(o))
                removeFlag = count;

        }

        if (removeFlag != -1) {

            for (int count = removeFlag + 1; count < size(); count++)
                array[count - 1] = array[count];

            array[size() - 1] = null;

            size--;

            result = true;

        }

        return result;

    }

    @Override
    public boolean containsAll(Collection<?> c) {

        boolean result = true;

        for (Object item: c) {

            if (!contains(item))
                result = false;

        }

        return result;

    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        for (Object item: c)
            add((T)item);

        return true;

    }

    @Override
    public boolean removeAll(Collection<?> c) {

        for (Object item: c)
            remove(item);

        return true;

    }

    @Override
    public boolean retainAll(Collection<?> c) {

        for (int count = 0; count < size(); count++) {

            if (!c.contains(array[count]))
                remove(array[count]);

        }

        return true;

    }

    @Override
    public void clear() {

        for (int count = 0; count < array.length; count++)
            array[count] = null;

        size = 0;

    }

    private class ArrayIterator implements Iterator<T> {

        private int index;

        @Override
        public boolean hasNext() {

            return index < size();

        }

        @Override
        public T next() {

            T result;

            if (!hasNext())
                throw new NoSuchElementException();

            result = array[index++];

            return result;

        }

    }

}
