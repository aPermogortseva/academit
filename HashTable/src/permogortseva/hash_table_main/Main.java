package permogortseva.hash_table_main;

import permogortseva.hash_table.HashTable;
import permogortseva.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10);

        hashTable.add(null);
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

        System.out.println("Массив, полученный из хэш-таблицы: " + Arrays.toString(hashTable.toArray(new Integer[1])));

        ArrayList<Integer> integers3 = new ArrayList<>(Arrays.asList(10, 2, 9));

        System.out.println("Все элементы, содержащиеся в списке удалены из хэш-таблицы:" + hashTable.removeAll(integers3));
        System.out.println("Хэш-таблица после удаления: " + hashTable);

        HashTable<Integer>.HashTableIterator iterator = hashTable.new HashTableIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ArrayList<Integer> integers4 = new ArrayList<>(Arrays.asList(null, 4, 3));

        System.out.println("Удалены элементы, не присутствующие в списке: " + hashTable.retainAll(integers4));
        System.out.println("Хэш-таблица после удаления элементов: " + hashTable);

        hashTable.clear();
        System.out.println("Очищенная хэш-таблица: " + hashTable);
    }
}
