package permogortseva.matrix_main;

import permogortseva.matrix.Matrix;
import permogortseva.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 4);
        System.out.println("Первая матрица: " + matrix);

        System.out.println("Количество строк первой матрицы: " + matrix.getRowsNumber());
        System.out.println("Количество столбцов первой матрицы: " + matrix.getColumnsNumber());

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
        System.out.println(Arrays.toString(arraysVector));

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

        matrix2.transpose();
        System.out.println("Транспонированная вторая матрица: " + matrix2);

        System.out.println("Третья матрица, помноженная на скаляр = 3: " + matrix3.multiply(3));

        double[][] array6 = {{2, 3, 6}, {4, 7, 5}, {8, 9, 1}};
        Matrix matrix6 = new Matrix(array6);
        System.out.println("Определитель матрицы = " + matrix6.getDeterminant());

        double[] array5 = {2.5, 4, 3};
        Vector vector5 = new Vector(array5);

        System.out.println("Произведение пятого вектора и шестой матрицы: " + matrix6.multiply(vector5));

        double[][] array7 = {{8, 34, 12}, {2, 67, 4}, {1, 5, 9}};
        Matrix matrix7 = new Matrix(array7);

        System.out.println("Произведение шестой и седьмой матриц: " + Matrix.multiply(matrix6, matrix7));

        matrix6.sum(matrix7);
        System.out.println("Шестая матрица, сложенная с седьмой: " + matrix6);

        matrix7.subtract(matrix6);
        System.out.println("Седьмая матрица, сложенная с шестой: " + matrix7);

        System.out.println("Сумма шестой и седьмой матриц: " + Matrix.sum(matrix6, matrix7));

        System.out.println("Вычитание шестой и седьмой матриц: " + Matrix.subtract(matrix6, matrix7));
    }
}
