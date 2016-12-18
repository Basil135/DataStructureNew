import java.lang.reflect.Array;
import java.util.*;

public class LinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {

        Item<T> currentItem = LinkedList.this.first;

        for (int count = 0; count < LinkedList.this.size(); count++) {

            if (currentItem.getElement().equals(o))
                return true;

            currentItem = currentItem.getNext();

        }

        return false;

    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    @Override
    public Object[] toArray() {

        Item<T> currentItem = LinkedList.this.first;

        Object[] array = new Object[LinkedList.this.size()];

        for (int count = 0; count < LinkedList.this.size(); count++) {

            array[count] = currentItem.getElement();

            currentItem = currentItem.getNext();
        }

        return array;

    }

    @Override
    public <T1> T1[] toArray(final T1[] a) {

        if (a == null)
            throw new NullPointerException();

        Object[] array = new Object[size()];

        array = this.toArray();

        if (a.length >= size()) {

            System.arraycopy(array, 0, a, 0, array.length);

            return a;

        } else {

            T1[] newA = (T1[]) Array.newInstance(a.getClass().getComponentType(), array.length);

            System.arraycopy(array, 0, newA, 0, array.length);

            return newA;

        }

    }

    @Override
    public boolean add(final T t) {

        if (t == null)
            throw new NullPointerException();

        Item<T> newItem = new Item<T>(t, null, null);

        push(newItem);

        size++;

        return true;

    }

    @Override
    public boolean remove(final Object o) {

        int index;

        Item<T> currentItem = LinkedList.this.first;

        for (int count = 0; count < LinkedList.this.size(); count++) {

            if (currentItem.getElement().equals(o)) {

                index = count;

                if (index == 0) {

                    deleteFirstItem();

                    return true;

                }

                if (index == LinkedList.this.size() - 1) {

                    pop();

                    return true;

                }

                deleteInMiddle(index);

                return true;

            }

            currentItem = currentItem.getNext();

        }

        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {

        if (c == null)
            throw new NullPointerException();

        for (Object item: c) {

            if (!LinkedList.this.contains(item))
                return false;

        }

        return true;

    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {

        if (c == null)
            throw new NullPointerException();

        for (Object item: c) {

            if (item == null)
                throw new NullPointerException();

            LinkedList.this.add((T)item);

        }

        return true;

    }

    @Override
    public boolean addAll(final int index, final Collection<? extends T> c) {

        int count = index;

        if (c == null)
            throw new NullPointerException();

        for (Object item: c) {

            if (item == null)
                throw new NullPointerException();

            LinkedList.this.add(count++, (T)item);

        }

        return true;

    }

    @Override
    public boolean removeAll(final Collection<?> c) {

        if (c == null)
            throw new NullPointerException();

        for (Object item: c) {

            if (item == null)
                throw new NullPointerException();

            LinkedList.this.remove(item);

        }

        return true;

    }

    @Override
    public boolean retainAll(final Collection<?> c) {

        if (c == null)
            throw new NullPointerException();


        for (Object item : c) {

            if (item == null)
                throw new NullPointerException();

            if (!LinkedList.this.contains(item))
                c.remove(item);

        }

        LinkedList.this.clear();

        for (Object item : c)
            LinkedList.this.add((T)item);

        return true;

    }

    @Override
    public void clear() {

        while (!LinkedList.this.isEmpty())
            LinkedList.this.remove(0);

    }

    @Override
    public T get(final int index) {

        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        if (index < size() / 2) {

            Item<T> currentItem = LinkedList.this.first;

            for (int count = 0; count < size() / 2; count++) {

                currentItem = currentItem.getNext();

            }

            return currentItem.getElement();

        } else {

            Item<T> currentItem = LinkedList.this.last;

            for (int count = size() - 1; count >= size() / 2; count--) {

                currentItem = currentItem.getPrev();

            }

            return currentItem.getElement();

        }

    }

    @Override
    public T set(final int index, final T element) {

        if (element == null)
            throw new NullPointerException();

        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        Item<T> currentItem;

        if (index < size() / 2) {

            currentItem = LinkedList.this.first;

            for (int count = 0; count < size() / 2; count++)
                currentItem = currentItem.getNext();

        } else {


            currentItem = LinkedList.this.last;

            for (int count = size() - 1; count >= size() / 2; count--)
                currentItem = currentItem.getPrev();

        }

        currentItem.element = element;

        return currentItem.getElement();

    }

    @Override
    public void add(final int index, final T element) {

        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        if (element == null)
            throw new NullPointerException();

        Item<T> newItem = new Item<T>(element, null, null);

        if (index == 0) {

            addFirstItem(newItem);

            size++;

        } else if (index == LinkedList.this.size() - 1) {

            push(newItem);

            size++;

        } else {

            addInMiddle(newItem, index);

            size++;
        }

    }

    @Override
    public T remove(final int index) {

        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        if (index == 0) {

            return deleteFirstItem().getElement();

        } else if (index == size() -  1) {

            return pop().getElement();

        } else {

            return deleteInMiddle(index).getElement();

        }

    }

    @Override
    public int indexOf(final Object o) {

        if (!(getClass() == o.getClass()))
            throw new ClassCastException();

        if (o == null)
            throw new NullPointerException();

        Item<T> currentItem = LinkedList.this.first;

        for (int count = 0; count < size(); count++) {

            if (currentItem.getElement().equals(o))
                return count;

            currentItem = currentItem.getNext();

        }

        return -1;

    }

    @Override
    public int lastIndexOf(final Object o) {
        if (!(getClass() == o.getClass()))
            throw new ClassCastException();

        if (o == null)
            throw new NullPointerException();

        Item<T> currentItem = LinkedList.this.last;

        for (int count = size() - 1; count >= 0; count--) {

            if (currentItem.getElement().equals(o))
                return count;

            currentItem = currentItem.getNext();

        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementIterator();
    }

    @Override
    public ListIterator<T> listIterator(final int index) {

        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        return new ElementIterator(index);

    }

    @Override
    public List<T> subList(final int fromIndex, final int toIndex) {

        if (fromIndex < 0 || toIndex < 0 || fromIndex > size() || toIndex > size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        LinkedList<T> newList = new LinkedList<T>();

        for (int count = fromIndex; count <= toIndex; count++)
            newList.add(LinkedList.this.get(count));

        return newList;

    }

    private void push(final Item<T> element) {

        //private case (when we push the first element)

        if (LinkedList.this.size() == 0) {

            LinkedList.this.first = element;

            LinkedList.this.last = element;

        }

        //usual case

        Item<T> prev = LinkedList.this.last;

        LinkedList.this.last = element;

        prev.next = element;

        element.prev = prev;

    }

    private Item<T> pop() {

        Item<T> lastItem = LinkedList.this.last;

        Item<T> prevItem = lastItem.getPrev();

        //private case (when we pop the last element)

        if (LinkedList.this.size() == 1) {

            LinkedList.this.first = null;

            LinkedList.this.last = null;

            size--;

            return lastItem;

        }

        //usual case

        LinkedList.this.last = lastItem.getPrev();

        prevItem.next = null;

        lastItem.prev = null;

        size--;

        return lastItem;

    }

    private void addFirstItem(final Item<T> element) {

        if (LinkedList.this.size() == 0) {

            LinkedList.this.first = element;

            LinkedList.this.last = element;

        }

        Item<T> firstItem = LinkedList.this.first;

        LinkedList.this.first = element;

        element.next = firstItem;

        firstItem.prev = element;

    }

    private Item<T> deleteFirstItem() {

        Item<T> firstItem = LinkedList.this.first;

        if (LinkedList.this.size() == 1) {

            LinkedList.this.first = null;

            LinkedList.this.last = null;

            size--;

            return firstItem;

        } else {

            Item<T> secondItem = firstItem.getNext();

            firstItem.next = null;

            secondItem.prev = null;

            LinkedList.this.first = secondItem;

            size--;

            return firstItem;

        }

    }

    private void addInMiddle(final Item<T> element, final int index) {

        Item<T> currentItem = LinkedList.this.first;

        for (int count = 0; count < index; count++)
            currentItem = currentItem.getNext();

        Item<T> nextItem = currentItem.getNext();

        currentItem.next = element;

        nextItem.prev = element;

        element.next = nextItem;

        element.prev = currentItem;

    }

    private Item<T> deleteInMiddle(final int index) {

        Item<T> currentItem = LinkedList.this.first;

        for (int count = 0; count < index; count++)
            currentItem = currentItem.getNext();

        Item<T> nextItem = currentItem.getNext();

        Item<T> prevItem = currentItem.getPrev();

        currentItem.prev = null;

        currentItem.next = null;

        prevItem.next = nextItem;

        nextItem.prev = prevItem;

        size--;

        return currentItem;

    }

    private class ElementIterator implements  ListIterator<T>{

        private int index;

        private int last = -1;

        public ElementIterator(final int index) {
            this.index = index;
        }

        public ElementIterator() {this.index = 0;}

        @Override
        public boolean hasNext() {
            return index < LinkedList.this.size();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item<T> current = LinkedList.this.first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            last = index;
            index++;
            return current.getElement();
        }

        @Override
        public boolean hasPrevious() {
            return last != -1;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            Item<T> current = LinkedList.this.first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current = current.getPrev();
            last = index - 1;
            index--;
            return current.getElement();
        }

        @Override
        public int nextIndex() {
            if (index == LinkedList.this.size() - 1)
                return LinkedList.this.size();
            return (LinkedList.this.size() - (index + 1));
        }

        @Override
        public int previousIndex() {
//            if (index == 0)
//                return -1;
            return index - 1;
        }

        @Override
        public void remove() {

            if (last == -1)
                throw new IllegalStateException();

            index--;

            last = -1;

            LinkedList.this.remove(index);

        }

        @Override
        public void set(final T t) {

            if (last == -1)
                throw new IllegalStateException();

            LinkedList.this.set(index, t);

        }

        @Override
        public void add(final T t) {

            LinkedList.this.add(t);

        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> next = null;

        private Item<T> prev = null;


        public Item(final T element, final Item<T> next, final Item<T> prev) {

            this.element = element;

            this.next = next;

            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }
    }
}
