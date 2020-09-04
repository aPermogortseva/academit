package permogortseva.shapes_main;

import permogortseva.shapes.*;
import permogortseva.shapes.comparators.AreaComparator;
import permogortseva.shapes.comparators.PerimeterComparator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] array = {new Circle(20.6), new Circle(3.9), new Triangle(1, 1, 2, 4, 9, 7),
                new Triangle(0, 1, 6, 2, 7, 5), new Rectangle(34.7, 10),
                new Rectangle(3.2, 8.9), new Square(3.4), new Square(9)};

        Arrays.sort(array, new AreaComparator());

        System.out.println("Фигура с наибольшей площадью: " + array[array.length - 1]);
        System.out.println("Площадь наибольшей фигуры = " + array[array.length - 1].getArea());

        Arrays.sort(array, new PerimeterComparator());

        System.out.println("Фигура со вторым периметром по величине: " + array[array.length - 2]);
        System.out.println("Периметр фигуры = " + array[array.length - 2].getArea());
    }
}
