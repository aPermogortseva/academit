package aPermShapes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] array = new Shape[8];

        array[0] = new Circle(20.6);
        array[1] = new Circle(3.9);

        array[2] = new Triangle(1, 1, 2, 4, 9, 7);
        array[3] = new Triangle(0, 1, 6, 2, 7, 5);

        array[4] = new Rectangle(34.7, 10);
        array[5] = new Rectangle(3.2, 8.9);

        array[6] = new Square(3.4);
        array[7] = new Square(9);

        Arrays.sort(array, new AreaComparator());

        System.out.println("Фигура с наибольшей площадью: " + array[array.length - 1].getClass() + ", " + array[array.length - 1]);
        System.out.println("Площадь наибольшей фигуры = " + array[array.length - 1].getArea());

        System.out.println("Фигура со второй площадью по величине: " + array[array.length - 2].getClass() + ", " + array[array.length - 2]);
        System.out.println("Площадь фигуры = " + array[array.length - 2].getArea());
    }
}
