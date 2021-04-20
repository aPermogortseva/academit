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
            throw new IndexOutOfBoundsException("Индекс должен быть <= размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }
    }

    public T get(int index) {
        checkIndex(index);

        ListItem<T> current = head;

        for (int i = 0; i < count; current = current.getNext(), i++) {
            if (i == index) {
                break;
            }
        }

        return current.getData();
    }

    private ListItem<T> getNode(int index) {
        ListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> current = getNode(index);

        T previousData = current.getData();
        current.setData(data);

        return previousData;
    }

    public T removeIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            T data = head.getData();
            head = head.getNext();

            count--;

            return data;
        }

        ListItem<T> current = getNode(index - 1);

        T deletedItemData = current.getNext().getData();

        current.setNext(current.getNext().getNext());

        count--;

        return deletedItemData;
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
            throw new IndexOutOfBoundsException("Индекс должен быть < размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> previous = getNode(index - 1);
            previous.setNext(new ListItem<>(data, previous.getNext()));

            count++;
        }
    }

    public boolean removeData(T data) {
        if (head.getData().equals(data)) {
            removeFirst();

            return true;
        }

        boolean isDeleted = false;

        for (ListItem<T> current = head.getNext(), previous = head; current != null; previous = current, current = current.getNext()) {
            if (current.getData().equals(data)) {
                previous.setNext(current.getNext());

                isDeleted = true;
                count--;

                break;
            }
        }

        return isDeleted;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Удаление первого элемента невозможно. Список пустой");
        }

        T deletedData = head.getData();
        head = head.getNext();

        count--;

        return deletedData;
    }

    public void reverse() {
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
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        newList.addFirst(head.getData());
        ListItem<T> copiedCurrent = newList.head;

        for (ListItem<T> originalCurrent = head; originalCurrent != null; originalCurrent = originalCurrent.getNext()) {
            copiedCurrent.setNext(originalCurrent.getNext());
            copiedCurrent = copiedCurrent.getNext();
        }

        return newList;
    }

    @Override
    public String toString() {
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
