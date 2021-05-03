package permogortseva.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Невозможно получить первый элемент, так как список пустой");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть < размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }
    }

    public T get(int index) {
        checkIndex(index);

        return getListItem(index).getData();
    }

    private ListItem<T> getListItem(int index) {
        ListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> current = getListItem(index);

        T oldData = current.getData();
        current.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previous = getListItem(index - 1);

        T removedData = previous.getNext().getData();

        previous.setNext(previous.getNext().getNext());

        count--;

        return removedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс должен быть <= размеру списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> previous = getListItem(index - 1);
        previous.setNext(new ListItem<>(data, previous.getNext()));

        count++;
    }

    public boolean removeByData(T data) {
        if (head.getData().equals(data)) {
            removeFirst();

            return true;
        }

        for (ListItem<T> current = head.getNext(), previous = head; current != null; previous = current, current = current.getNext()) {
            if (data.equals(current.getData())) {
                previous.setNext(current.getNext());

                count--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Удаление первого элемента невозможно. Список пустой");
        }

        T removedData = head.getData();
        head = head.getNext();

        count--;

        return removedData;
    }

    public void reverse() {
        if (count == 0) {
            return;
        }

        ListItem<T> previous = null;
        ListItem<T> current = head;
        ListItem<T> next = current.getNext();

        while (next != null) {
            current.setNext(previous);
            previous = current;
            current = next;

            next = current.getNext();
        }

        current.setNext(previous);

        head = current;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copiedList = new SinglyLinkedList<>();

        if (count == 0) {
            return copiedList;
        }

        copiedList.addFirst(head.getData());
        ListItem<T> copiedCurrent = copiedList.head;

        for (ListItem<T> originalCurrent = head; originalCurrent.getNext() != null; originalCurrent = originalCurrent.getNext()) {
            copiedCurrent.setNext(new ListItem<>(originalCurrent.getNext().getData()));

            copiedCurrent = copiedCurrent.getNext();
        }

        return copiedList;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData());
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append('}').toString();
    }
}
