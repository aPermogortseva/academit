package permogortseva.array_list_main;

import permogortseva.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array1 = {1, null, 9};
        ArrayList<Integer> integers1 = new ArrayList<>(array1);

        ArrayList<Integer> integers3 = new ArrayList<>(array1);

        System.out.println(integers1.equals(integers3));

        System.out.println(integers1);

        ArrayList<Double> doubles1 = new ArrayList<>(5);

        doubles1.add(2.0);
        doubles1.add(4.2);
        doubles1.add(2.0);
        doubles1.add(null);
        doubles1.add(6.0);
        doubles1.add(7.2);

        System.out.println("Индекс последнего вхождения элемента: " + doubles1.lastIndexOf(2.0));

        System.out.println("Проверка, что элемент найден и удалён: " + doubles1.remove(2.0));
        System.out.println("Список после удаления: " + doubles1);

        ArrayList<Double>.ArrayLisIterator iterator = doubles1.new ArrayLisIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Удалённый элемент: " + doubles1.remove(1));
        System.out.println("Список после удаления элемента: " + doubles1);

        ArrayList<Integer> integers2 = new ArrayList<>(Arrays.asList(3, 4, 5, 5, 1));
        System.out.println("Список целых чисел: " + integers2);

        System.out.println("Проверка, что в списке есть заданный элемент: " + integers2.contains(1));

        System.out.println("Массив из списка из метода toArray(): " + Arrays.toString(integers2.toArray()));

        Integer[] array2 = new Integer[8];

        array2 = integers2.toArray(array2);
        System.out.println("Массив, полученный из списка: " + Arrays.toString(array2));

        doubles1.add(3, 8.0);
        System.out.println(doubles1);

        ArrayList<Double> doubles2 = new ArrayList<>(Arrays.asList(2.1, 3.9, 6.0, 9.8));

        doubles1.addAll(4, doubles2);
        System.out.println("Список после прибавления к нему другого списка: " + doubles1);

        System.out.println("Индекс, по которому расположен 6.0: " + doubles1.indexOf(6.0));

        doubles1.clear();
        System.out.println("Очищенный список вещественных чисел: " + doubles1);

        ArrayList<Double> doubles3 = new ArrayList<>(Arrays.asList(2.1, 6.0, 8.4));

        doubles3.retainAll(doubles2);
        System.out.println("Третий список, состоящий только из элементов, присутствующих во втором списке: " + doubles3);

        ArrayList<Double> doubles4 = new ArrayList<>();
        doubles4.add(2.1);
        doubles4.add(6.0);
        doubles4.add(3.3);

        System.out.println("Проверка, что 4й список полностью содержит 3й: " + doubles4.containsAll(doubles3));

        doubles4.removeAll(doubles3);
        System.out.println("Четвертый список после удаления из него всех элементов из третьего списка: " + doubles4);

        Double[] doublesArray = doubles4.toArray(new Double[6]);
        System.out.println("Массив, полученный из списка: " + Arrays.toString(doublesArray));
    }
}
