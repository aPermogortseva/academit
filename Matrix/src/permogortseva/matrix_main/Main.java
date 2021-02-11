package permogortseva.matrix_main;

import permogortseva.matrix.Matrix;
import permogortseva.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 4);
        System.out.println("Первая матрица: " + matrix);
        System.out.println("Размеры первой матрицы: " + Arrays.toString(matrix.getSize()));

        double[] array = {3, 6, 7, 9, 8, 5};
        Vector vector = new Vector(array);

        double[] array2 = {2.4, 8, 5, 7.2};
        Vector vector2 = new Vector(array2);

        double[] array3 = {3, 6, 7, 19};
        Vector vector3 = new Vector(array3);

        Vector[] arraysVector = new Vector[3];
        arraysVector[0] = vector;
        arraysVector[1] = vector2;
        arraysVector[2] = vector3;

        Matrix matrix2 = new Matrix(arraysVector);
        System.out.println("Вторая матрица: " + matrix2);

        double[][] array4 = {{2, 4.5, 7}, {1.2, 3.4, 78, 5.3}, {3, 5}, {0, 8.7, 4}};
        Matrix matrix3 = new Matrix(array4);
        System.out.println("Третья матрица: " + matrix3);

        Matrix matrix4 = new Matrix(matrix3);
        System.out.println("Четвертая матрица: " + matrix4);

        int index = 0;

        System.out.println("Строка четвертой матрицы по индексу = " + index + " : " + matrix4.getRow(index));

        Vector vector4 = new Vector(array2);

        matrix4.setRow(0, vector4);
        System.out.println("Новое значение строки четвертой матрицы: " + matrix4.getRow(index));

        Vector column = matrix4.getColumn(0);
        System.out.println("Вектор-столбец четвертой матрицы: " + column);

        System.out.println("Транспонированная вторая матрица: " + matrix2.transpose());

        System.out.println("Третья матрица, помноженная на скаляр = 3: " + matrix3.multiply(3));

        double[][] array5 = {{9, 8, 7, 6}, {5, 4, 3, 2}, {1, 0, 1, 2}, {3, 4, 5, 6}};
        Matrix matrix5 = new Matrix(array5);
        //System.out.println(matrix5.getDeterminant());

        double[][] array6 = {{2, 3, 6}, {4, 7, 5}, {8, 9, 1}};
        Matrix matrix6 = new Matrix(array6);
        System.out.println(matrix6.getDeterminant());
    }
}
