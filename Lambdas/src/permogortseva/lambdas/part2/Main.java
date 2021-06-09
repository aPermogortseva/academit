package permogortseva.lambdas.part2;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество чисел для вычисления:");
        int numbersCount = scanner.nextInt();

        if (numbersCount <= 0) {
            throw new IllegalArgumentException("Количество чисел должно быть > 0");
        }

        DoubleStream squareRoots = DoubleStream.iterate(1, x -> x + 1)
                .map(Math::sqrt)
                .limit(numbersCount);

        System.out.println("Квадратные корни чисел: ");
        squareRoots.forEach(System.out::println);
    }
}
