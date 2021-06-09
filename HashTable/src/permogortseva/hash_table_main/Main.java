package permogortseva.hash_table_main;

import permogortseva.hash_table.HashTable;
import permogortseva.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(5);

        hashTable.add(null);
        hashTable.add(4);
        hashTable.add(3);
        hashTable.add(2);
        hashTable.add(2);
        hashTable.add(1);

        System.out.println("Хэш-таблица содержит заданный элемент: " + hashTable.contains(null));

        System.out.println("Проверка, что элемент найден и удалён: " + hashTable.remove(9));

        System.out.println("размер хэш-таблицы: " + hashTable.size());

        System.out.println(hashTable);

        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(2, null, 4));
        System.out.println("Проверка, что хэш-таблица содержит все элементы из списка: " + hashTable.containsAll(integers1));

        hashTable.addAll(integers1);

        HashTable<Integer>.HashTableIterator iterator = hashTable.new HashTableIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Массив, полученный из хэш-таблицы: " + Arrays.toString(hashTable.toArray()));

        ArrayList<Integer> integers3 = new ArrayList<>(Arrays.asList(10, 2, 9));

        System.out.println("Все элементы, соедержащиеся в списке удалены из хэш-таблицы:" + hashTable.removeAll(integers3));
        System.out.println("Хэш-таблица после удаления: " + hashTable);

        ArrayList<Integer> integers4 = new ArrayList<>(Arrays.asList(1, 2, 4, null));

        System.out.println("Сохранены все элементы, присутствующие в списке: " + hashTable.retainAll(integers4));
        System.out.println("Хэш-таблица после сохранения: " + hashTable);

        hashTable.clear();
        System.out.println("Очищенная хэш-таблица: " + hashTable);
    }
}
