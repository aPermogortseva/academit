package permogortseva.list_main;

import permogortseva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();

        integers.addFirst(4);
        integers.addFirst(3);
        integers.addFirst(null);
        integers.addFirst(9);
        integers.addFirst(null);
        integers.addFirst(7);

        System.out.println("Размер списка: " + integers.getCount());

        System.out.println("Первый элемент списка: " + integers.getFirst());
        System.out.println("Удалённый первый элемент: " + integers.removeFirst());
        System.out.println("Односвязный список: " + integers);

        int index = 3;
        System.out.println("Элемент по индексу " + index + ": " + integers.get(index));

        System.out.println("Старое занчение по индексу " + index + ": " + integers.set(index, null));
        System.out.println("Односвязный список: " + integers);

        System.out.println("Удалённый элемент: " + integers.removeByIndex(2));

        System.out.println("Односвязный список: " + integers);

        System.out.println("Заданный элемент удален из списка: " + integers.removeByData(null));
        System.out.println("Односвязный список: " + integers);

        integers.add(0, 4);
        integers.add(3, 6);
        System.out.println("Односвязный список: " + integers);

        integers.reverse();
        System.out.println("Развернутый список: " + integers);

        SinglyLinkedList<Integer> listCopy = integers.copy();
        System.out.println("Скопированный список: " + listCopy);
    }
}