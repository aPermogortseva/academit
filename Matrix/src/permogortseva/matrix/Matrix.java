package permogortseva.matrix;

import permogortseva.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rowsArray;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Неверный размер = " + n + ". Размер не может быть меньше 1");
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Неверный размер = " + m + ". Размер не может быть меньше 1");
        }

        rowsArray = new Vector[n];

        for (int i = 0; i < n; i++) {
            rowsArray[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        rowsArray = new Vector[matrix.getSize()[0]];

        for (int i = 0; i < rowsArray.length; i++) {
            rowsArray[i] = new Vector(matrix.rowsArray[i]);
        }
    }

    public Matrix(double[][] array) {
        rowsArray = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rowsArray[i] = new Vector(array[i]);
        }

        addZeros(rowsArray);
    }

    public Matrix(Vector[] vector) {
        rowsArray = Arrays.copyOf(vector, vector.length);

        addZeros(rowsArray);
    }

    private void addZeros(Vector[] rowsArray) {
        int maxSize = rowsArray[0].getSize();

        for (Vector e : rowsArray) {
            if (e.getSize() > maxSize) {
                maxSize = e.getSize();
            }
        }

        for (Vector e : rowsArray) {
            if (e.getSize() < maxSize) {
                Vector emptyArray = new Vector(maxSize);
                e.add(emptyArray);
            }
        }
    }

    public int[] getSize() {
        int rowsCount = rowsArray.length;
        int columnsCount = rowsArray[0].getSize();

        return new int[]{rowsCount, columnsCount};
    }

    public Vector getRow(int index) {
        if (index >= rowsArray.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора. " +
                    "Размерность вектора = " + rowsArray.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        return rowsArray[index];
    }

    public void setRow(int index, Vector vector) {
        if (index >= rowsArray.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора. " +
                    "Размерность вектора = " + rowsArray.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        if (vector.getSize() != rowsArray[0].getSize()) {
            throw new IllegalArgumentException("Вектор-строка имеет неверную дину = " + vector.getSize() +
                    ". Длина вектора-строки должна быть равна количеству столбцов матрицы. Количество столбцов матрицы = " +
                    rowsArray[0].getSize());
        }

        rowsArray[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index >= rowsArray[0].getSize()) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора. " +
                    "Размерность вектора = " + rowsArray.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        Vector column = new Vector(rowsArray.length);

        for (int i = 0; i < rowsArray.length; i++) {
            double temp = rowsArray[i].getElement(index);
            column.setElement(i, temp);
        }

        return column;
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(rowsArray[0].getSize(), rowsArray.length);

        for (int i = 0; i < rowsArray[0].getSize(); i++) {
            Vector temp = new Vector(this.getColumn(i));
            transposedMatrix.setRow(i, temp);
        }

        return transposedMatrix;
    }

    public Matrix multiply(double scalar) {
        for (Vector vector : rowsArray) {
            vector.multiply(scalar);
        }

        return this;
    }

    public double getDeterminant() {
        if (rowsArray[0].getSize() != rowsArray.length) {
            throw new IllegalArgumentException("Получение определителя возможно, если матрица квадратная. Ширина матрицы = " +
                    rowsArray.length + "длина матрицы = " + rowsArray[0].getSize());
        }

        if (this.rowsArray.length == 1) {
            return rowsArray[0].getElement(0);
        }

        if (this.rowsArray[0].getSize() == 2) {
            return rowsArray[0].getElement(0) * rowsArray[1].getElement(1) - rowsArray[0].getElement(1) * rowsArray[1].getElement(0);
        }

        double determinant = 0;

        Matrix minor = new Matrix(rowsArray.length - 1, rowsArray.length - 1);

        for (int i = 0; i < rowsArray.length; i++) {
            for (int j = 0; j < minor.rowsArray.length; j++) {
                int index = 0;

                for (int k = 0; k < rowsArray.length; k++) {
                    if (k != i) {
                        minor.rowsArray[j].setElement(index, this.rowsArray[j + 1].getElement(k));
                        index++;
                    }
                }
            }

            determinant += Math.pow(-1, i + 2) * this.rowsArray[0].getElement(i) * minor.getDeterminant();
        }

        return determinant;
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != rowsArray.length) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству строк матрицы. Длина вектора = " +
                    vector.getSize() + ". Количество строк в матрице = " + rowsArray.length);
        }

        Vector resultVector = new Vector(rowsArray[0].getSize());

        for (int i = 0; i < resultVector.getSize(); i++) {
            double elementsSum = 0;

            for (int j = 0; j < rowsArray.length; j++) {
                elementsSum += vector.getElement(j) * rowsArray[j].getElement(i);
            }

            resultVector.setElement(i, elementsSum);
        }

        return resultVector;
    }

    public void getSum(Matrix matrix) {
        if (this.getSize()[0] != matrix.getSize()[0] || this.getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: " + Arrays.toString(this.getSize()) +
                    ". Размеры второй матрицы: " + Arrays.toString(matrix.getSize()));
        }

        for (int i = 0; i < rowsArray.length; i++) {
            for (int j = 0; j < rowsArray.length; j++) {
                rowsArray[i].setElement(j, rowsArray[i].getElement(j) + matrix.rowsArray[i].getElement(j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (this.getSize()[0] != matrix.getSize()[0] || this.getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: " + Arrays.toString(this.getSize()) +
                    ". Размеры второй матрицы: " + Arrays.toString(matrix.getSize()));
        }

        for (int i = 0; i < rowsArray.length; i++) {
            for (int j = 0; j < rowsArray.length; j++) {
                rowsArray[i].setElement(j, rowsArray[i].getElement(j) - matrix.rowsArray[i].getElement(j));
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize()[0] != matrix2.getSize()[0] || matrix1.getSize()[1] != matrix2.getSize()[1]) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: " + Arrays.toString(matrix1.getSize()) +
                    ". Размеры второй матрицы: " + Arrays.toString(matrix2.getSize()));
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.getSum(matrix2);

        return newMatrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize()[0] != matrix2.getSize()[0] || matrix1.getSize()[1] != matrix2.getSize()[1]) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: " + Arrays.toString(matrix1.getSize()) +
                    ". Размеры второй матрицы: " + Arrays.toString(matrix2.getSize()));
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize()[1] != matrix2.getSize()[0]) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов первой матрицы, должно быть равно количеству строк второй." +
                    " Количество столбцов первой матрицы: " + (matrix1.getSize()[1]) +
                    ". Количество строк второй матрицы: " + matrix2.getSize()[0]);
        }

        Matrix newMatrix = new Matrix(matrix1.getSize()[0], matrix2.getSize()[1]);

        for (int i = 0; i < newMatrix.rowsArray[0].getSize(); i++) {
            for (int l = 0; l < newMatrix.rowsArray[0].getSize(); l++) {
                double elementsSum = 0;

                for (int m = 0; m < newMatrix.rowsArray[0].getSize(); m++) {
                    elementsSum += matrix1.rowsArray[l].getElement(m) * matrix2.rowsArray[m].getElement(i);
                }

                newMatrix.rowsArray[l].setElement(i, elementsSum);
            }
        }

        return newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (Vector e : rowsArray) {
            sb.append(e);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append('}').toString();
    }
}