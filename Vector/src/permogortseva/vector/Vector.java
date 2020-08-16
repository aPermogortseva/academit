package permogortseva.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] array;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер не может быть меньше 1");
        }

        array = new double[size];
    }

    public Vector(Vector vector) {
        size = vector.size;
        array = vector.array;
    }

    public Vector(double[] array) {
        size = array.length;
        this.array = array;
    }

    public Vector(int n, double[] array) {
        size = n;

        if (n <= 0) {
            throw new IllegalArgumentException("Размер не может быть меньше 1");
        }

        this.array = new double[n];

        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public int getSize() {
        return size;
    }

    public void add(Vector vector) {
        if (this.size >= vector.size) {
            for (int i = 0; i < vector.size; i++) {
                array[i] += vector.array[i];
            }

            return;
        }

        size = vector.array.length;

        double[] temp = array;
        array = new double[vector.array.length];

        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }

        for (int i = 0; i < this.size; i++) {
            array[i] += vector.array[i];
        }

    }

    public void subtract(Vector vector) {
        if (this.size >= vector.size) {
            for (int i = 0; i < vector.size; i++) {
                array[i] -= vector.array[i];
            }

            return;
        }

        size = vector.array.length;

        double[] temp = array;
        array = new double[vector.array.length];

        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }

        for (int i = 0; i < this.size; i++) {
            array[i] -= vector.array[i];
        }

    }

    public void multiply(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= scalar;
        }

    }

    public void reverse() {
        for (int i = 0; i < array.length; i++) {
            array[i] *= -1;
        }

    }

    public double getElement(int index) {
        if (index > array.length) {
            throw new IllegalArgumentException("Индекс не модет быть больше размерности вектора");
        }

        return array[index];
    }

    public void setElement(int index, double value) {
        if (index > array.length) {
            throw new IllegalArgumentException("Индекс не может быть больше размерности вектора");
        }

        array[index] = value;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;

        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(array);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return size == vector.size && Arrays.equals(array, vector.array);
    }

    public static Vector add(Vector firstVector, Vector secondVector) {
        if (firstVector.size > secondVector.size) {
            Vector newVector = new Vector(firstVector);

            for (int i = 0; i < secondVector.size; i++) {
                newVector.array[i] += secondVector.array[i];
            }

            return newVector;
        }

        Vector newVector = new Vector(secondVector);

        for (int i = 0; i < firstVector.size; i++) {
            newVector.array[i] += firstVector.array[i];
        }

        return newVector;
    }

    public static Vector subtract(Vector firstVector, Vector secondVector) {
        if (firstVector.size > secondVector.size) {
            Vector newVector = new Vector(firstVector);

            for (int i = 0; i < secondVector.size; i++) {
                newVector.array[i] -= secondVector.array[i];
            }

            return newVector;
        }

        Vector newVector = new Vector(secondVector);

        for (int i = 0; i < firstVector.size; i++) {
            newVector.array[i] -= firstVector.array[i];
        }

        return newVector;
    }

    public static double multiplyVectors(Vector firstVector, Vector secondVector) {
        double result = 0;

        if (firstVector.size >= secondVector.size) {
            for (int i = 0; i < secondVector.size; i++) {
                result += firstVector.array[i] * secondVector.array[i];
            }

            return result;
        }

        for (int i = 0; i < firstVector.size; i++) {
            result += firstVector.array[i] * secondVector.array[i];
        }

        return result;
    }
}
