package permogortseva.matrix;

import permogortseva.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Неверное количество строк = " + rows + ". Количество строк не может быть меньше 1");
        }

        if (columns <= 0) {
            throw new IllegalArgumentException("Неверное количество столбцов = " + columns + ". Количество столбцов не может быть меньше 1");
        }

        this.rows = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsNumber()];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Неверное количество строк в исходном массиве = " +
                    array.length +
                    ". Количество строк матрицы не может быть меньше 1");
        }

        rows = new Vector[array.length];

        int maxSize = array[0].length;

        for (double[] e : array) {
            if (e.length > maxSize) {
                maxSize = e.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Неверное количество строк исходного вектора = " +
                    vectorsArray.length +
                    ". Количетсво строк не может быть меньше 1");
        }
        boolean arrayIsEmpty = true;

        for (Vector e : vectorsArray) {
            if (e != null) {
                arrayIsEmpty = false;
                break;
            }
        }

        if (arrayIsEmpty) {
            throw new IllegalArgumentException("В исходном векторе отсутствуют столбцы. Количество столбцов не может быть меньше 1");
        }

        rows = Arrays.copyOf(vectorsArray, vectorsArray.length);

        addZeros(this);
    }

    private static void addZeros(Matrix matrix) {
        int maxSize = 0;

        for (Vector e : matrix.rows) {
            if (e != null && e.getSize() > maxSize) {
                maxSize = e.getSize();
            }
        }

        Vector zerosVector = new Vector(maxSize);

        for (int i = 0; i < matrix.rows.length; i++) {
            if (matrix.rows[i] == null) {
                matrix.rows[i] = new Vector(zerosVector);
            } else if (matrix.rows[i].getSize() < maxSize) {
                matrix.rows[i] = Vector.getSum(matrix.rows[i], zerosVector);
            }
        }
    }

    public int getRowsNumber() {
        return rows.length;
    }

    public int getColumnsNumber() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора. " +
                    "Размерность вектора = " + rows.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора. " +
                    "Размерность вектора = " + rows.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Вектор-строка имеет неверную длину = " + vector.getSize() +
                    ". Длина вектора-строки должна быть равна количеству столбцов матрицы. Количество столбцов матрицы = " +
                    rows[0].getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше количества столбцов матрицы. " +
                    "Количество столбцов матрицы = " + rows.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть >= 0");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setElement(i, rows[i].getElement(index));
        }

        return column;
    }

    public void transpose() {
        Matrix transposedMatrix = new Matrix(rows[0].getSize(), rows.length);

        for (int i = 0; i < rows[0].getSize(); i++) {
            Vector temp = new Vector(this.getColumn(i));
            transposedMatrix.setRow(i, temp);
        }

        rows = Arrays.copyOf(transposedMatrix.rows, transposedMatrix.rows.length);

    }

    public Matrix multiply(double scalar) {
        for (Vector vector : rows) {
            vector.multiply(scalar);
        }

        return this;
    }

    public double getDeterminant() {
        if (rows[0].getSize() != rows.length) {
            throw new IllegalArgumentException("Получение определителя возможно, если матрица квадратная. Количество строк матрицы = " +
                    getRowsNumber() + ", колиечтсво столбцов матрицы = " + getColumnsNumber());
        }

        if (this.rows.length == 1) {
            return rows[0].getElement(0);
        }

        if (this.rows[0].getSize() == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        double determinant = 0;

        Matrix minor = new Matrix(rows.length - 1, rows.length - 1);

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < minor.rows.length; j++) {
                int index = 0;

                for (int k = 0; k < rows.length; k++) {
                    if (k != i) {
                        minor.rows[j].setElement(index, this.rows[j + 1].getElement(k));
                        index++;
                    }
                }
            }

            determinant += Math.pow(-1, i + 2) * this.rows[0].getElement(i) * minor.getDeterminant();
        }

        return determinant;
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != rows.length) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству строк матрицы. Длина вектора = " +
                    vector.getSize() + ". Количество строк в матрице = " + getRowsNumber());
        }

        Vector resultVector = new Vector(rows[0].getSize());

        for (int i = 0; i < resultVector.getSize(); i++) {
            resultVector.setElement(i, Vector.getScalarProduct(vector, getColumn(i)));
        }

        return resultVector;
    }

    public void sum(Matrix matrix) {
        if (getRowsNumber() != matrix.getRowsNumber() || getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + getRowsNumber() + " , " + getColumnsNumber() + ". Размеры второй матрицы: " + matrix.getRowsNumber() +
                    " , " + matrix.getColumnsNumber());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsNumber() != matrix.getRowsNumber() || getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + getRowsNumber() + " , " + getColumnsNumber() + ". Размеры второй матрицы: " + matrix.getRowsNumber() +
                    " , " + matrix.getColumnsNumber());
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                rows[i].subtract(matrix.rows[i]);
            }
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + matrix1.getRowsNumber() + " , " + matrix1.getColumnsNumber() + ". Размеры второй матрицы: " + matrix2.getRowsNumber() +
                    " , " + matrix2.getColumnsNumber());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.sum(matrix2);

        return newMatrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + matrix1.getRowsNumber() + " , " + matrix1.getColumnsNumber() + ". Размеры второй матрицы: " + matrix2.getRowsNumber() +
                    " , " + matrix2.getColumnsNumber());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.getRowsNumber()) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов первой матрицы, должно быть равно количеству строк второй." +
                    " Количество столбцов первой матрицы: " + (matrix1.getColumnsNumber()) +
                    ". Количество строк второй матрицы: " + matrix2.getRowsNumber());
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsNumber(), matrix2.getColumnsNumber());

        for (int i = 0; i < newMatrix.rows[0].getSize(); i++) {
            for (int j = 0; j < newMatrix.rows[0].getSize(); j++) {
                double elementsSum = 0;

                for (int k = 0; k < newMatrix.rows[0].getSize(); k++) {
                    elementsSum += matrix1.rows[j].getElement(k) * matrix2.rows[k].getElement(i);
                }

                newMatrix.rows[j].setElement(i, elementsSum);
            }
        }

        return newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (Vector e : rows) {
            sb.append(e);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append('}').toString();
    }
}