package aPermRange;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range leftRange = new Range(3.7, 69);

        leftRange.setFrom(4.1);
        leftRange.setTo(70);

        System.out.println("Начало левого диапазона = " + leftRange.getFrom());
        System.out.println("Конец левого диапазона = " + leftRange.getTo());


        double point = 4.5;

        System.out.println("Точка лежит в левом диапазоне: " + leftRange.isInside(point));
        System.out.println("Длина левого диапазона = " + leftRange.getLength());

        Range rightRange = new Range(70, 80);

        System.out.println("Пересечение диапазонов: " + leftRange.getIntersection(rightRange));

        System.out.println("Объединение множеств: " + Arrays.toString(leftRange.getUnionRange(rightRange)));

        System.out.println("Разность множеств: " + Arrays.toString(leftRange.getComplementRange(rightRange)));
    }
}
