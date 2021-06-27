package permogortseva.hash_table;

import permogortseva.array_list.ArrayList;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_LENGTH = 10;

    private final ArrayList<E>[] lists;

    private int modCount = 0;
    private int size = 0;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_ARRAY_LENGTH];
    }

    public HashTable(int arrayLength) {
        if (arrayLength < 1) {
            throw new IllegalArgumentException("Длина массива должна быть >= 1");
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[arrayLength];
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
    public boolean contains(Object o) {
        int index = getArrayListIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    public class HashTableIterator implements Iterator<E> {
        private int currentArrayIndex = 0;
        private int indexInList = -1;
        private int indexInHashTable = -1;

        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return indexInHashTable + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Невозможно получить следующий элемент. Достигнут конец хэш-таблицы");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Произошло изменение хэш-таблицы, Iterator() невозможен. " +
                        "Количество изменений до итерирования = " + initialModCount + ". Количество изменений вовремя итерирования = " + modCount);
            }


            if (lists[currentArrayIndex] != null && indexInList + 1 == lists[currentArrayIndex].size()) {
                currentArrayIndex++;
                indexInList = -1;
            }

            while (lists[currentArrayIndex] == null || lists[currentArrayIndex].size() == 0) {
                currentArrayIndex++;
            }

            indexInList++;
            indexInHashTable++;

            return lists[currentArrayIndex].get(indexInList);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (E e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            T[] newArray = (T[]) toArray();

            //noinspection unchecked
            array = (T[]) Arrays.copyOf(newArray, size, array.getClass());

            return array;
        }

        int i = 0;

        for (E e : this) {
            //noinspection unchecked
            array[i] = (T) e;
            i++;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    private int getArrayListIndex(Object o) {
        return Math.abs(Objects.hashCode(o) % lists.length);
    }

    @Override
    public boolean add(E element) {
        int index = getArrayListIndex(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);

        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getArrayListIndex(o);

        if (lists[index] == null) {
            return false;
        }

        boolean isRemoved = lists[index].remove(o);

        if (isRemoved) {
            size--;
            modCount++;
        }

        return isRemoved;
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
    public boolean addAll(Collection<? extends E> c) {
        int modCountBeforeAdding = modCount;

        for (E e : c) {
            add(e);
        }

        return modCountBeforeAdding != modCount;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isChanged = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int listSizeBeforeRemoving = list.size();

                list.removeAll(c);

                int difference = listSizeBeforeRemoving - list.size();

                if (difference > 0) {
                    size -= difference;

                    isChanged = true;
                }
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int removedElementsCount = 0;
        boolean isChanged = false;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                int listSizeBeforeRetaining = lists[i].size();

                if (lists[i].retainAll(c)) {
                    removedElementsCount += listSizeBeforeRetaining - lists[i].size();
                    isChanged = true;
                }

                if (lists[i].size() == 0) {
                    lists[i] = null;
                }
            }
        }

        if (isChanged) {
            modCount++;
        }

        size -= removedElementsCount;

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (ArrayList<E> list : lists) {
            sb.append(list);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append(']').toString();
    }
}
