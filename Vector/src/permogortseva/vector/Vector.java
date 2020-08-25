package permogortseva.vector;

import java.util.Arrays;

public class Vector {
    private double[] componentsArray;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер не может быть меньше 1");
        }

        componentsArray = new double[size];
    }

    public Vector(Vector vector) {
        componentsArray = new double[vector.componentsArray.length];

        System.arraycopy(vector.componentsArray, 0, componentsArray, 0, componentsArray.length);
    }

    public Vector(double[] array) {
        componentsArray = new double[array.length];

        System.arraycopy(array, 0, componentsArray, 0, componentsArray.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер не может быть меньше 1");
        }

        componentsArray = new double[size];

        System.arraycopy(array, 0, componentsArray, 0, componentsArray.length);
    }

    public int getSize() {
        return componentsArray.length;
    }

    public void add(Vector vector) {
        if (componentsArray.length < vector.componentsArray.length) {
            double[] temp = componentsArray;
            componentsArray = new double[vector.componentsArray.length];

            System.arraycopy(temp, 0, componentsArray, 0, temp.length);
        }

        for (int i = 0; i < Math.min(vector.componentsArray.length, componentsArray.length); i++) {
            componentsArray[i] += vector.componentsArray[i];
        }
    }

    public void subtract(Vector vector) {
        if (componentsArray.length < vector.componentsArray.length) {
            double[] temp = componentsArray;
            componentsArray = new double[vector.componentsArray.length];

            System.arraycopy(temp, 0, componentsArray, 0, temp.length);
        }

        for (int i = 0; i < Math.min(vector.componentsArray.length, componentsArray.length); i++) {
            componentsArray[i] -= vector.componentsArray[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < componentsArray.length; i++) {
            componentsArray[i] *= scalar;
        }

    }

    public void reverse() {
        multiply(-1);
    }

    public double getElement(int index) {
        if (index >= componentsArray.length) {
            throw new IllegalArgumentException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        return componentsArray[index];
    }

    public void setElement(int index, double value) {
        if (index >= componentsArray.length) {
            throw new IllegalArgumentException("Неверный индекс = " + index + ". Индекс должен быть меньше размерности вектора");
        }

        if (index < 0) {
            throw new IllegalArgumentException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        componentsArray[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (double e : componentsArray) {
            sb.append(e);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return "{" + sb.toString() + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;

        hash = prime * hash + componentsArray.length;
        hash = prime * hash + Arrays.hashCode(componentsArray);

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
        return componentsArray.length == vector.componentsArray.length && Arrays.equals(componentsArray, vector.componentsArray);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1.componentsArray.length > vector2.componentsArray.length) {
            Vector newVector = new Vector(vector1);

            newVector.add(vector2);

            return newVector;
        }

        Vector newVector = new Vector(vector2);

        newVector.add(vector1);

        return newVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1.componentsArray.length > vector2.componentsArray.length) {
            Vector newVector = new Vector(vector1);

            newVector.subtract(vector2);

            return newVector;
        }

        Vector newVector = new Vector(vector2);

        newVector.subtract(vector1);

        return newVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector2.componentsArray.length, vector1.componentsArray.length); i++) {
            result += vector1.componentsArray[i] * vector2.componentsArray[i];
        }

        return result;
    }
}