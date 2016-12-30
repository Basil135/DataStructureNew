import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<E> implements Collection<E> {

    private E[] dataArray = (E[])new Object[1];

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Object o) {

        if (o == null)
            throw new NullPointerException();

        if (!(o instanceof E))
            throw new ClassCastException();


        for (int count = 0; count < size; count++) {

            if (dataArray[count].equals(o))
                return true;

        }

        return false;

    }

    @Override
    public Iterator iterator() {
        return new arrayIterator();
    }

    @Override
    public Object[] toArray() {
        return dataArray;
    }

    @Override
    public <T> T[] toArray(final T[] a) {

        if (a == null)
            throw new NullPointerException();

        for (int count = 0; count < size; count++) {

            if (a.getClass().isInstance(dataArray[count]))
                throw  new ArrayStoreException();

        }

        if (a.length >= size) {

            System.arraycopy(dataArray, 0, a, 0, size - 1);

            if (a.length > size)
                a[size] = null;

            return a;

        } else {

            T[] newA = (T[]) Array.newInstance(a.getClass().getComponentType(), size);

            System.arraycopy(dataArray, 0, newA, 0, size);

            return newA;

        }

    }

    @Override
    public boolean add(final E e) {

       if (size < dataArray.length) {

           dataArray[size++] = e;

       } else {

           E[] newDataArray = (E[])new Object[size * 2];

           System.arraycopy(dataArray, 0, newDataArray, 0, size);

           dataArray = newDataArray;

           dataArray[size++] = e;

       }

        return true;
    }

    @Override
    public boolean remove(final Object o) {

        if (o == null)
            throw new NullPointerException();

        for (int count = 0; count < size; count++) {

            if (dataArray[count].equals(o)) {

                for (int i = count; i < size - 1; i++) {
                    dataArray[i] = dataArray[i+1];
                }

                size--;

                dataArray[size] = null;

            }

        }

        return true;

    }

    @Override
    public boolean addAll(Collection c) {

        for (Object item: c) {

            if (item == null)
                throw new NullPointerException();

            if (!dataArray.getClass().isInstance(item))
                throw new IllegalStateException();

            add((E)item);

        }

        return true;

    }

    @Override
    public void clear() {

        for (int count = 0; count < size; count++) {

            dataArray[count] = null;

        }

        size = 0;

    }

    @Override
    public boolean retainAll(Collection c) {

        for (int i = 0; i < size; i++) {

            if (!c.contains(dataArray[i])) {

                remove(dataArray[i]);

            }

        }

        return true;

    }

    @Override
    public boolean removeAll(Collection c) {

        for (Object item: c) {

            remove(item);

        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {

        for (Object item: c) {

            if (!contains(item))
                return false;

        }

        return true;

    }

    private class arrayIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Object next() {

            if (!hasNext())
                throw new NoSuchElementException();

            return dataArray[index++];
        }
    }
}
