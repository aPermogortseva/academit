package permogortseva.matrix;

import permogortseva.vector.Vector;

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

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Неверное количество строк исходного вектора = " +
                    vectorsArray.length + ". Количетсво строк не может быть меньше 1");
        }

        int maxSize = 0;

        for (Vector e : vectorsArray) {
            if (e == null) {
                throw new NullPointerException("Невозможно создать матрицу, тк один из векторов = null");
            }

            if (e.getSize() > maxSize) {
                maxSize = e.getSize();
            }
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxSize);
            rows[i].add(vectorsArray[i]);
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
        if (getColumnsCount() == getRowsCount()) {
            for (int i = 0; i < getColumnsCount(); i++) {
                rows[i] = getColumn(i);
            }
        } else {
            Matrix transposedMatrix = new Matrix(getColumnsCount(), getRowsCount());

            for (int i = 0; i < getColumnsCount(); i++) {
                transposedMatrix.rows[i] = getColumn(i);
            }

            rows = transposedMatrix.rows;
        }
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

    public Vector multiply(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству столбцов матрицы. Длина вектора = " +
                    vector.getSize() + ". Количество столбцов в матрице = " + getColumnsCount());
        }

        Vector resultVector = new Vector(getColumnsCount());

        for (int i = 0; i < resultVector.getSize(); i++) {
            resultVector.setElement(i, Vector.getScalarProduct(vector, getColumn(i)));
        }

        return resultVector;
    }

    private static void checkMatrixSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны. Размеры первой матрицы: "
                    + matrix1.getRowsCount() + ", " + matrix1.getColumnsCount() + ". Размеры второй матрицы: " + matrix2.getRowsCount() +
                    ", " + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Для умножения матриц количество столбцов первой матрицы, должно быть равно количеству строк второй." +
                    " Количество столбцов первой матрицы: " + matrix1.getColumnsCount() +
                    ". Количество строк второй матрицы: " + matrix2.getRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < newMatrix.getColumnsCount(); i++) {
            for (int j = 0; j < newMatrix.getRowsCount(); j++) {
                newMatrix.rows[j].setElement(i, Vector.getScalarProduct(matrix1.getRow(j), matrix2.getColumn(i)));
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