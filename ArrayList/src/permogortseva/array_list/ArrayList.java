package permogortseva.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private int capacity = 10;
    private int size;
    private int modCount;
    private E[] elements;

    public ArrayList() {
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(int initialCapacity) {
        capacity = initialCapacity;
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(E[] array) {
        elements = Arrays.copyOf(array, array.length);
        size = array.length;
    }

    public ArrayList(List<E> list) {
        elements = (E[]) new Object[list.size()];
        size = list.size();

        for (int i = 0; i < size; i++) {
            elements[i] = list.get(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object data) {
        ArrayLisIterator iterator = new ArrayLisIterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(data)) {
                return true;
            }
        }

        return false;
    }

    public class ArrayLisIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCount = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Невозможно получить следующий элемент. Достигнут конец списка");
            }

            if (modCount != ArrayList.this.modCount) {
                throw new ConcurrentModificationException("Произошло изменение списка, Iterator() невозможен. " +
                        "Количество изменений до итерирования = " + modCount + ". Количество изменений вовремя итерирования = " + ArrayList.this.modCount);
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayLisIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
        capacity = elements.length;
    }

    private void ensureCapacity(int size) {
        if (size == capacity) {
            increaseCapacity();
        }
    }

    @Override
    public boolean add(E element) {
        int modCountBeforeAdding = modCount;
        ensureCapacity(size);

        elements[size] = element;

        size++;
        modCount++;

        return modCount != modCountBeforeAdding;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size - 2; i++) {
            if (element.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - 1);

                size--;
                modCount++;

                return true;
            }
        }

        if (element.equals(elements[size - 1])) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int modCountBeforeAdding = modCount;
        ensureCapacity(size + c.size());

        ArrayList<E> collection = (ArrayList<E>) c;
        ArrayLisIterator iterator = collection.new ArrayLisIterator();

        while (iterator.hasNext()) {
            add(iterator.next());
        }

        return modCountBeforeAdding != modCount;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int modCountBeforeAdding = modCount;

        if (index == size) {
            addAll(c);

            return modCountBeforeAdding != modCount;
        }

        checkIndex(index);
        ensureCapacity(size + c.size());

        ArrayList<E> collection = (ArrayList<E>) c;
        ArrayLisIterator iterator = collection.new ArrayLisIterator();

        while (iterator.hasNext()) {
            add(index, iterator.next());
            index++;
        }

        return modCountBeforeAdding != modCount;
    }

    @Override
    public void clear() {
        size = 0;

        modCount++;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0, и <= длины списка. Индекс = " + index +
                    ". Длина списка = " + size);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        modCount++;

        return elements[index] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0, и < длины списка. Индекс = " + index +
                    ". Длина списка = " + size);
        }

        ensureCapacity(size);

        if (index == size) {
            add(element);
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = element;

            size++;
            modCount++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        if (index == size - 1) {
            size--;
            modCount++;

            return elements[index];
        }

        E deletedElement = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - 1);

        size--;
        modCount++;

        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                isChanged = true;
            }
        }

        modCount++;

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);
            }
        }

        modCount++;

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        ArrayList<E> collection = (ArrayList<E>) c;
        ArrayLisIterator iterator = collection.new ArrayLisIterator();

        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length == 0) {
            return (T[]) toArray();
        }

        if (array.length < size) {
            array = (T[]) Arrays.copyOf(elements, size, array.getClass());
        }

        System.arraycopy(elements, 0, array, 0, size);

        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            sb.append(", ");
        }

        if (!isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append(']').toString();
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;

        hash = prime * hash + size;
        hash = prime * hash + capacity;
        hash = prime * hash + Arrays.hashCode(elements);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        ArrayList<E> list = (ArrayList<E>) o;

        return size == list.size && capacity == list.capacity && Arrays.equals(elements, list.elements);
    }
}
