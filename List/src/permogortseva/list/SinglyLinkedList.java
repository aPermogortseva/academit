package permogortseva.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        ListItem<T> current = head;
        return current.getData();
    }

    public T getItem(int index) {
        if (head == null) {
            throw new NullPointerException("Неверный индекс: " + index + ". Список пустой");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        ListItem<T> current = head;

        for (int i = 0; i < count; current = current.getNext(), i++) {
            if (i == index) {
                break;
            }
        }

        return current.getData();
    }

    public T changeItem(int index, T newData) {
        if (head == null) {
            throw new NullPointerException("Неверный индекс: " + index + ". Список пустой");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        ListItem<T> current = head;

        for (int i = 0; i <= index; i++) {
            current = current.getNext();
        }

        T temp = current.getData();

        current.setData(newData);

        return temp;
    }

    public T deleteItem(int index) {
        if (head == null) {
            throw new NullPointerException("Неверный индекс: " + index + ". Список пустой");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        if (index == 0) {
            T data = head.getData();
            head = head.getNext();

            count--;

            return data;
        }

        ListItem<T> current = head;

        if (index == count - 1) {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            current.setNext(null);

            count--;

            return current.getNext().getData();
        }

        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        T deletedItemData = current.getNext().getData();

        current.setNext(current.getNext().getNext());

        count--;

        return deletedItemData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(T data, int index) {
        if (head == null) {
            throw new NullPointerException("Неверный индекс: " + index + ". Список пустой");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше размера списка. " +
                    "Индекс = " + index + ". Размер списка = " + count);
        }

        ListItem<T> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        ListItem<T> newItem = new ListItem<>(data, current.getNext());
        current.setNext(newItem);

        count++;
    }

    public boolean remove(T data) {
        boolean isDeleted = false;

        if (head.getData() == data) {
            deleteFirst();

            isDeleted = true;
        }

        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (current.getData() == data && previous != null) {
                previous.setNext(current.getNext());

                isDeleted = true;

                count--;
            }
        }

        return isDeleted;
    }

    public T deleteFirst() {
        T temp = head.getData();
        head = head.getNext();

        count--;

        return temp;
    }

    public void reverse() {
        for (int end = count; end > 0; end--) {
            add(head.getData(), end);
            deleteFirst();
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        newList.addFirst(head.getData());

        ListItem<T> current = head;

        for (int i = 1; i < count; i++, current = current.getNext()) {
            newList.add(getItem(i), i);
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
