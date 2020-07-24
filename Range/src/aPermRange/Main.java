package aPermRange;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(3.7, 69);

        double point = 4.5;

        System.out.println("Точка лежит в диапазоне: " + range.isInside(point));
        System.out.println("Длина диапазона = " + range.getLength());
    }
}
