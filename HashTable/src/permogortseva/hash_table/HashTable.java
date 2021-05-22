package permogortseva.hash_table;

import permogortseva.array_list.ArrayList;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int modCount = 0;

    public HashTable(int size) {
        lists = (ArrayList<E>[]) new ArrayList[size];
    }

    @Override
    public int size() {
        return lists.length;
    }

    @Override
    public boolean isEmpty() {
        for (ArrayList<E> e : lists) {
            if (e != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode() % lists.length);

        if (lists[index] == null) {
            return false;
        } else return lists[index].contains(o);
    }

    public class HashtableIterator implements Iterator<E> {
        private int currentIndex = 0;
        private int indexInArrayList = -1;
        private final int modCount = HashTable.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 <= lists.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Невозможно получить следующий элемент. Достигнут конец списка");
            }

            if (modCount != HashTable.this.modCount) {
                throw new ConcurrentModificationException("Произошло изменение списка, Iterator() невозможен. " +
                        "Количество изменений до итерирования = " + modCount + ". Количество изменений вовремя итерирования = " + HashTable.this.modCount);
            }

            if (lists[currentIndex] == null) {
                currentIndex++;

                return null;
            }

            if (indexInArrayList + 2 == lists[currentIndex].size()) {
                indexInArrayList = -1;
                currentIndex++;

                return lists[currentIndex - 1].get(lists[currentIndex - 1].size() - 1);
            } else {
                indexInArrayList++;

                return lists[currentIndex].get(indexInArrayList);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashtableIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(lists, lists.length);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < lists.length) {
            array = (T[]) Arrays.copyOf(lists, lists.length, array.getClass());
        }

        System.arraycopy(lists, 0, array, 0, lists.length);

        if (array.length > lists.length) {
            array[lists.length] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = Math.abs(element.hashCode() % lists.length);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);

        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % lists.length);

        if (lists[index] == null) {
            return false;
        }

        boolean isRemoved = lists[index].remove(o);

        if (isRemoved && lists[index].size() == 0) {
            lists[index] = null;
        }

        modCount++;

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            int index = Math.abs(o.hashCode() % lists.length);

            if (lists[index] == null || !lists[index].contains(o)) {
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

        int modCountBeforeRemoving = modCount;

        for (Object o : c) {
            while (contains(o)) {
                remove(o);
            }
        }

        return modCountBeforeRemoving != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }

            if (!c.contains(lists[i].get(0))) {
                lists[i] = null;
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        for (ArrayList<E> list : lists) {
            list.clear();
        }

        modCount++;
    }

    @Override
    public String toString() {
        if (lists.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (ArrayList<E> list : lists) {
            sb.append(list);
            sb.append(", ");
        }

        if (!isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append(']').toString();
    }
}
