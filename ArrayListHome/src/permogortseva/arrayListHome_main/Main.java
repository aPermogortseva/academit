package permogortseva.arrayListHome_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> writeFileToList(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(fileName));

        ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }

        return list;
    }

    public static void removeOddNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static <E> ArrayList<E> removeRepeats(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();

        for (E e : list) {
            if (!newList.contains(e)) {
                newList.add(e);
            }
        }

        return newList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1;
        list1 = writeFileToList("arrayListHomeInput.txt");
        System.out.println("Список строк из файла : " + list1);

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, -10, 4, 5, 6, 7));
        removeOddNumbers(list2);
        System.out.println("Второй список с удалёнными чётными числами: " + list2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(2, 3, 4, 4, 3, 6, 7));
        System.out.println("Третий список без повторов: " + removeRepeats(list3));
    }
}