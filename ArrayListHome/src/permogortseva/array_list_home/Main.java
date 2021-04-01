package permogortseva.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> getFileStringsList(String fileName){
        ArrayList<String> list = new ArrayList<>();

        try(Scanner scanner = new Scanner(new FileInputStream(fileName));) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static <E> ArrayList<E> makeUnique(ArrayList<E> list) {
        ArrayList<E> resultList = new ArrayList<>(list.size());

        for (E e : list) {
            if (!resultList.contains(e)) {
                resultList.add(e);
            }
        }

        return resultList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String>  stringsList = getFileStringsList("arrayListHomeInput.txt");
        System.out.println("Список строк из файла : " + stringsList);

        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(2, 3, -10, 4, 5, 6, 7));
        removeEvenNumbers(integers1);
        System.out.println("Второй список с удалёнными чётными числами: " + integers1);

        ArrayList<Integer> integers2 = new ArrayList<>(Arrays.asList(2, 3, 4, 4, 3, 6, 7));
        System.out.println("Третий список без повторов: " + makeUnique(integers2));
    }
}