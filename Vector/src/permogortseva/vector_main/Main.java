package permogortseva.vector_main;

import permogortseva.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {2, 4, 6};

        Vector vector = new Vector(7, array);
        System.out.println("Вектор: " + vector.toString());

        System.out.println("Размер вектора = " + vector.getSize());

        double[] array2 = {3, 6, 7, 9, 8, 5};
        Vector vector2 = new Vector(array2);

        System.out.println("Второй вектор: " + vector2.toString());

        vector.add(vector2);
        System.out.println("Первый вектор, сложенный со вторым = " + vector.toString());

        double[] array3 = {13, 5.6, 17, 0.7};
        Vector vector3 = new Vector(array3);

        System.out.println("Третий вектор: " + vector3.toString());

        vector2.subtract(vector3);
        System.out.println("Второй вектор минус третий вектор: " + vector2.toString());

        double scalar = 2.5;

        vector3.multiply(scalar);
        System.out.println("Третий вектор, помноженый на скаляр " + scalar + ": " + vector3.toString());

        vector3.reverse();
        System.out.println("Развёрнутый третий вектор: " + vector3.toString());

        vector3.setElement(2, 6);

        double component = vector3.getElement(2);
        System.out.println("Новое значение компоненты по индексу 2: " + component);

        double[] array4 = {-32.5, -14.0, 6, -1.75};
        Vector vector4 = new Vector(array4);

        System.out.println("Проверка на равенство третьего и четвертого векторов: " + vector3.equals(vector4));

        System.out.println("Сумма третьего и четвертого вектров: " + Vector.getSum(vector3, vector4));

        System.out.println("Разность третьего и четвертого вектров: " + Vector.getDifference(vector3, vector4));

        double[] array5 = {2.5, 4, 3, 5, 8};
        Vector vector5 = new Vector(array5);

        System.out.println("Длина пятого вектора: " + vector5.getLength());

        System.out.println("Скалярное произведение четвертого и пятого векторов: " + Vector.getScalarProduct(vector4, vector5));
    }
}
