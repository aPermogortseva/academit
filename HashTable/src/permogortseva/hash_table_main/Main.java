package permogortseva.hash_table_main;

import permogortseva.array_list.ArrayList;
import permogortseva.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);

        hashTable.add(1);
        hashTable.add(1);
        hashTable.add(3);
        hashTable.add(2);
        hashTable.add(8);
        hashTable.add(5);

        System.out.println("Проверка, что элемент найден и удалён: " + hashTable.remove(5));

        System.out.println(hashTable);

        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(5, 9, 4));
        System.out.println("Проверка, что хэш-таблица содержит все элементы из списка: " + hashTable.containsAll(integers1));

        hashTable.addAll(integers1);

        HashTable<Integer>.HashtableIterator iterator = hashTable.new HashtableIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ArrayList<Integer>[] integers2 = hashTable.toArray(new ArrayList[11]);
        System.out.println("Массив, полученный из хэш-таблицы: " + Arrays.toString(integers2));

        ArrayList<Integer> integers3 = new ArrayList<>(Arrays.asList(10, 3, 9));

        System.out.println("Все элементы, соедержащиеся в списке удалены из хэш-таблицы:" + hashTable.removeAll(integers3));
        System.out.println("Хэш-таблица после удаления: " + hashTable);

        ArrayList<Integer> integers4 = new ArrayList<>(Arrays.asList(1, 2, 30));

        System.out.println("Сохранены все элементы, присутствующие в списке: " + hashTable.retainAll(integers4));
        System.out.println("Хэш-таблица после сохранения: " + hashTable);
    }
}
