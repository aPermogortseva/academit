package permogortseva.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private final int initialCapacity = 10;
    private int size;
    private int modCount;
    private E[] elements;

    public ArrayList() {
        elements = (E[]) new Object[initialCapacity];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость должна быть >= 0");
        }

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
        return indexOf(data) >= 0;
    }

    public class ArrayLisIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Невозможно получить следующий элемент. Достигнут конец списка");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Произошло изменение списка, Iterator() невозможен");
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayLisIterator();
    }

    private void increaseCapacity() {
        if (elements.length == 0) {
            elements = Arrays.copyOf(elements, initialCapacity);

            modCount++;
        }

        elements = Arrays.copyOf(elements, elements.length * 2);

        modCount++;
    }

    public void ensureCapacity(int size) {
        if (size > elements.length) {
            elements = Arrays.copyOf(elements, size);

            modCount++;
        }
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdding(index);

        if (size == elements.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;

        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object object) {
        int indexToRemove = indexOf(object);

        if (indexToRemove == -1) {
            return false;
        }

        remove(indexOf(object));

        return true;
    }


    @Override
    public E remove(int index) {
        checkIndex(index);

        if (size == elements.length) {
            increaseCapacity();
        }

        E removedElement = elements[index];

        if (index == size - 1) {
            elements[index] = null;

            size--;
            modCount++;

            return removedElement;
        }

        elements[index] = null;
        System.arraycopy(elements, index + 1, elements, index, size - index);

        size--;
        modCount++;

        return removedElement;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int modCountBeforeAdding = modCount;

        addAll(size, c);

        return modCountBeforeAdding != modCount;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexForAdding(index);

        int modCountBeforeAdding = modCount;

        ensureCapacity(size + c.size());

        if (index != size) {
            System.arraycopy(elements, index, elements, index + c.size(), size - index);
        }

        for (E e : c) {
            elements[index] = e;

            index++;

            size++;
            modCount++;
        }

        return modCountBeforeAdding != modCount;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = size - 1; i >= 0; i--) {
            elements[i] = null;
        }

        size = 0;
        modCount++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0, и < длины списка. Индекс = " + index +
                    ". Длина списка = " + size);
        }
    }

    private void checkIndexForAdding(int index) {
        if (index < 0 || index > size) {
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

        E previous = elements[index];
        elements[index] = element;

        return previous;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
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

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int modCountBeforeRemoving = modCount;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);
            }
        }

        return modCountBeforeRemoving != modCount;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            array = (T[]) Arrays.copyOf(elements, size, array.getClass());
        }

        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);

            modCount++;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

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

        return Arrays.equals(elements, list.elements);
    }
}
