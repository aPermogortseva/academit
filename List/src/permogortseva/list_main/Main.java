package permogortseva.list_main;

import permogortseva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();

        integers.addFirst(6);
        integers.addFirst(3);
        integers.addFirst(2);
        integers.addFirst(4);
        integers.addFirst(9);

        System.out.println("Размер списка: " + integers.getSize());

        System.out.println("Первый элемент списка: " + integers.getFirst());
        System.out.println("Удалённый первый элемент: " + integers.deleteFirst());
        System.out.println("Односвязный список: " + integers);

        int index = 2;
        System.out.println("Элемент по индексу " + index + ": " + integers.getItem(index));

        System.out.println("Старое занчение по индексу " + index + ": " + integers.changeItem(index, 5));
        System.out.println("Односвязный список: " + integers);

        System.out.println("Удалённый элемент: " + integers.deleteItem(2));
        System.out.println("Односвязный список: " + integers);

        System.out.println("Заданный элемент удален из списка: " + integers.remove(4));
        System.out.println("Односвязный список: " + integers);

        integers.add(4, 0);
        integers.add(6, 2);
        System.out.println("Односвязный список: " + integers);

        integers.reverse();
        System.out.println("Развернутый список: " + integers);

        SinglyLinkedList<Integer> listCopy = integers.copy();
        System.out.println("Скопированный список: " + listCopy);
    }
}