package permogortseva.matrix;

import permogortseva.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Неверное количество строк = " + rowsCount + ". Количество строк не может быть меньше 1");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Неверное количество столбцов = " + columnsCount + ". Количество столбцов не может быть меньше 1");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Неверное количество строк в исходном массиве = " +
                    array.length + ". Количество строк матрицы не может быть меньше 1");
        }

        rows = new Vector[array.length];

        int maxColumnsCount = array[0].length;

        for (double[] e : array) {
            if (e.length > maxColumnsCount) {
                maxColumnsCount = e.length;
            }
        }

        if (maxColumnsCount == 0) {
            throw new IllegalArgumentException("Неверное максимальное количество столбцов в исходном массиве = " +
                    maxColumnsCount + ". Количество столбцов матрицы не может быть меньше 1");
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Неверное количество строк исходного вектора = " +
                    vectorsArray.length + ". Количетсво строк не может быть меньше 1");
        }

        boolean isArrayEmpty = true;

        for (Vector e : vectorsArray) {
            if (e != null) {
                isArrayEmpty = false;
                break;
            }
        }

        if (isArrayEmpty) {
            throw new IllegalArgumentException("В исходном векторе отсутствуют столбцы. Количество столбцов не может быть меньше 1");
        }

        rows = Arrays.copyOf(vectorsArray, vectorsArray.length);

        int maxSize = 0;

        for (Vector e : rows) {
            if (e != null && e.getSize() > maxSize) {
                maxSize = e.getSize();
            }
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null) {
                rows[i] = new Vector(maxSize);
            } else if (rows[i].getSize() < maxSize) {
                Vector temp = new Vector(rows[i]);
                rows[i] = new Vector(maxSize);
                rows[i].add(temp);
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Неверный индекс строки = " + index + ". Индекс должен быть меньше количества строк матрицы. " +
                    "Количество строк матрицы = " + rows.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс строки матрицы = " + index + ". Индекс должен быть >= 0");
        }

        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Неверный индекс строки = " + index + ". Индекс должен быть меньше количества строк матрицы. " +
                    "Количество строк матрицы = " + rows.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс строки = " + index + ". Индекс должен быть >= 0");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Строка имеет неверную длину = " + vector.getSize() +
                    ". Длина строки должна быть равна количеству столбцов матрицы. Количество столбцов матрицы = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть меньше количества столбцов матрицы. " +
                    "Количество столбцов матрицы = " + getColumnsCount());
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
        Matrix transposedMatrix = new Matrix(getColumnsCount(), getRowsCount());

        for (int i = 0; i < getColumnsCount(); i++) {
            Vector temp = new Vector(getColumn(i));
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
        if (getColumnsCount() != getRowsCount()) {
            throw new UnsupportedOperationException("Получение определителя возможно, если матрица квадратная. Количество строк матрицы = " +
                    getRowsCount() + ", колиечтсво столбцов матрицы = " + getColumnsCount());
        }

        if (getColumnsCount() == 1) {
            return rows[0].getElement(0);
        }

        if (getColumnsCount() == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        double determinant = 0;

        Matrix minor = new Matrix(rows.length - 1, rows.length - 1);

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < minor.rows.length; j++) {
                int index = 0;

                for (int k = 0; k < rows.length; k++) {
                    if (k != i) {
                        minor.rows[j].setElement(index, rows[j + 1].getElement(k));
                        index++;
                    }
                }
            }

            determinant += Math.pow(-1, i + 2) * rows[0].getElement(i) * minor.getDeterminant();
        }

        return determinant;
    }

    public Matrix multiply(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству столбцов матрицы. Длина вектора = " +
                    vector.getSize() + ". Количество столбцов в матрице = " + getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(getRowsCount(), 1);

        for (int i = 0; i < resultMatrix.getRowsCount(); i++) {
            resultMatrix.rows[i].setElement(0, Vector.getScalarProduct(vector, getRow(i)));
        }

        return resultMatrix;
    }

    private boolean isSizeDifference(Matrix matrix) {
        return getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount();
    }

    public void add(Matrix matrix) {
        if (isSizeDifference(matrix)) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + getRowsCount() + " , " + getColumnsCount() + ". Размеры второй матрицы: " + matrix.getRowsCount() +
                    " , " + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (isSizeDifference(matrix)) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + getRowsCount() + " , " + getColumnsCount() + ". Размеры второй матрицы: " + matrix.getRowsCount() +
                    " , " + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.isSizeDifference(matrix2)) {
            throw new IllegalArgumentException("Для сложения матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + matrix1.getRowsCount() + " , " + matrix1.getColumnsCount() + ". Размеры второй матрицы: " + matrix2.getRowsCount() +
                    " , " + matrix2.getColumnsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getSubtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.isSizeDifference(matrix2)) {
            throw new IllegalArgumentException("Для вычитания матриц их размеры должны быть равны. Размеры первой матрицы: "
                    + matrix1.getRowsCount() + " , " + matrix1.getColumnsCount() + ". Размеры второй матрицы: " + matrix2.getRowsCount() +
                    " , " + matrix2.getColumnsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов первой матрицы, должно быть равно количеству строк второй." +
                    " Количество столбцов первой матрицы: " + (matrix1.getColumnsCount()) +
                    ". Количество строк второй матрицы: " + matrix2.getRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < newMatrix.getColumnsCount(); i++) {
            for (int j = 0; j < newMatrix.getColumnsCount(); j++) {
                double elementsSum = 0;

                for (int k = 0; k < newMatrix.getColumnsCount(); k++) {
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