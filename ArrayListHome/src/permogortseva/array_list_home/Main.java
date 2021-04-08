package permogortseva.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> getFileStringsList(String fileName) {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName));) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл: " + fileName);
        }

        return list;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static <E> ArrayList<E> getUniqueValuesList(ArrayList<E> list) {
        ArrayList<E> resultList = new ArrayList<>(list.size());

        for (E e : list) {
            if (!resultList.contains(e)) {
                resultList.add(e);
            }
        }

        return resultList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> strings = getFileStringsList("arrayListHomeInput.txt");
        System.out.println("Список строк из файла : " + strings);

        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(2, 3, -10, 4, 5, 6, 7));
        removeEvenNumbers(integers);
        System.out.println("Второй список с удалёнными чётными числами: " + integers);

        ArrayList<Integer> integersWithRepeats = new ArrayList<>(Arrays.asList(2, 3, 4, 4, 3, 6, 7));
        System.out.println("Третий список без повторов: " + getUniqueValuesList(integersWithRepeats));
    }
}