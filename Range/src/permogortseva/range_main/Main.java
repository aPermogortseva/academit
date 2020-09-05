package permogortseva.range_main;

import permogortseva.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(3.7, 69);

        range1.setFrom(1);
        range1.setTo(5);

        System.out.println("Начало левого диапазона = " + range1.getFrom());
        System.out.println("Конец левого диапазона = " + range1.getTo());

        double point = 4.5;

        System.out.println("Точка лежит в левом диапазоне: " + range1.isInside(point));
        System.out.println("Длина левого диапазона = " + range1.getLength());

        Range range2 = new Range(1, 3);

        System.out.println("Пересечение диапазонов: " + range1.getIntersection(range2));

        System.out.println("Объединение множеств: " + Arrays.toString(range1.getUnion(range2)));

        System.out.println("Разность множеств: " + Arrays.toString(range1.getComplement(range2)));
    }
}
